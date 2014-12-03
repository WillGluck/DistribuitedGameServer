
struct Player {
  1: string name,
  2: list<i32> position,
  3: i32 life,
  4: i32 area
  5: i64 last_saved
}

struct Attack {
	1: string attcker,
	2: string attacked
}

service Game {

   string login(1:string user),
   Player get_player(1:string user),
   bool move_self(1:Player player),
   list<Player> update_players(1:i32 region),
   bool attack(1:Attack attack),
   list<Attack> update_self(1:string name),
   string go_to_area(1:i32 area, 2:Player player)

}