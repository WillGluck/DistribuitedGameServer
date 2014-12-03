from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

from thrift.game.gameservice import Client
from thrift.game.ttypes import Player, Attack

def get_client(server):
	transport = TSocket.TSocket(server, 9090)
	transport = TTransport.TBufferedTransport(transport)
	protocol = TBinaryProtocol.TBinaryProtocol(transport)
	client = Client(protocol)
	return (client, transport)

client, transport = get_client('127.0.0.1')
transport.open()
username = str(raw_input("input username: "))
server = client.login(username)
print server
transport.close()

client, transport = get_client(server)
transport.open()
players = client.update_players(1)
player = client.get_player(username)

while True:
	cmd = str(raw_input('Command: '))
	if 'walk' in cmd:
		current_position = player.position
		if 'up' in cmd:
			new_position = (current_position[0]-1, current_position[1])
		elif 'down' in cmd:
			new_position = (current_position[0]+1, current_position[1])
		elif 'right' in cmd:
			new_position = (current_position[0], current_position[1]+1)
		elif 'left' in cmd:
			new_position = (current_position[0], current_position[1]-1)
		else:
			print 'Wrong command'

		player.position = new_position
		if client.move_self(player):
			print 'Moved to ', player.position
		else:
			player.position = current_position
			print 'Cannot move to this position'

	elif 'attack' in cmd:
		try:
			attacked = cmd.split(' ')[1]
			attack = Attack()
			attack.attcker = player.name
			attack.attacked = attacked
			if client.attack(attack):
				print 'Attacked player ', attacked
		except:
			print 'Failed to attack'
	elif 'list' in cmd and 'players' in cmd:
		for other_player in players:
			print other_player.name, 'is in postion ', other_player.position, ' with life ', other_player.life
	elif 'goto' in cmd:
		try:
			area = int(cmd.split(' ')[1])
			area_server = client.go_to_area(area, player)
			if len(area_server):
				transport.close()
				client, transport = get_client(area_server)
				transport.open()
				player = client.get_player(username)
				print 'You are now in area ', area
			else:
				print 'Cannot go to this area'
		except:
			print 'Cannot go to this area'
	else:
		print 'Wrong command try again'

	attacks = client.update_self(player.name)
	for attack in attacks:
		player.life -= 10
		print 'You have been attacked by ', attack.attcker, ' your life is now ', player.life
	players = client.update_players(player.area)
	print ''