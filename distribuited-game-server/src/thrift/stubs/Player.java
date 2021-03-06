package thrift.stubs;

/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2014-12-2")
public class Player implements org.apache.thrift.TBase<Player, Player._Fields>, java.io.Serializable, Cloneable, Comparable<Player> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Player");

  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField POSITION_FIELD_DESC = new org.apache.thrift.protocol.TField("position", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField LIFE_FIELD_DESC = new org.apache.thrift.protocol.TField("life", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField AREA_FIELD_DESC = new org.apache.thrift.protocol.TField("area", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField LAST_SAVED_FIELD_DESC = new org.apache.thrift.protocol.TField("last_saved", org.apache.thrift.protocol.TType.I64, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PlayerStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PlayerTupleSchemeFactory());
  }

  public String name; // required
  public List<Integer> position; // required
  public int life; // required
  public int area; // required
  public long last_saved; // required
  public long attackCooldown;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NAME((short)1, "name"),
    POSITION((short)2, "position"),
    LIFE((short)3, "life"),
    AREA((short)4, "area"),
    LAST_SAVED((short)5, "last_saved");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NAME
          return NAME;
        case 2: // POSITION
          return POSITION;
        case 3: // LIFE
          return LIFE;
        case 4: // AREA
          return AREA;
        case 5: // LAST_SAVED
          return LAST_SAVED;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __LIFE_ISSET_ID = 0;
  private static final int __AREA_ISSET_ID = 1;
  private static final int __LAST_SAVED_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.POSITION, new org.apache.thrift.meta_data.FieldMetaData("position", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32))));
    tmpMap.put(_Fields.LIFE, new org.apache.thrift.meta_data.FieldMetaData("life", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.AREA, new org.apache.thrift.meta_data.FieldMetaData("area", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.LAST_SAVED, new org.apache.thrift.meta_data.FieldMetaData("last_saved", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Player.class, metaDataMap);
  }

  public Player() {
  }

  public Player(
    String name,
    List<Integer> position,
    int life,
    int area,
    long last_saved)
  {
    this();
    this.name = name;
    this.position = position;
    this.life = life;
    setLifeIsSet(true);
    this.area = area;
    setAreaIsSet(true);
    this.last_saved = last_saved;
    setLast_savedIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Player(Player other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetPosition()) {
      List<Integer> __this__position = new ArrayList<Integer>(other.position);
      this.position = __this__position;
    }
    this.life = other.life;
    this.area = other.area;
    this.last_saved = other.last_saved;
  }

  public Player deepCopy() {
    return new Player(this);
  }

  @Override
  public void clear() {
    this.name = null;
    this.position = null;
    setLifeIsSet(false);
    this.life = 0;
    setAreaIsSet(false);
    this.area = 0;
    setLast_savedIsSet(false);
    this.last_saved = 0;
  }

  public String getName() {
    return this.name;
  }

  public Player setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public int getPositionSize() {
    return (this.position == null) ? 0 : this.position.size();
  }

  public java.util.Iterator<Integer> getPositionIterator() {
    return (this.position == null) ? null : this.position.iterator();
  }

  public void addToPosition(int elem) {
    if (this.position == null) {
      this.position = new ArrayList<Integer>();
    }
    this.position.add(elem);
  }

  public List<Integer> getPosition() {
    return this.position;
  }

  public Player setPosition(List<Integer> position) {
    this.position = position;
    return this;
  }

  public void unsetPosition() {
    this.position = null;
  }

  /** Returns true if field position is set (has been assigned a value) and false otherwise */
  public boolean isSetPosition() {
    return this.position != null;
  }

  public void setPositionIsSet(boolean value) {
    if (!value) {
      this.position = null;
    }
  }

  public int getLife() {
    return this.life;
  }

  public Player setLife(int life) {
    this.life = life;
    setLifeIsSet(true);
    return this;
  }

  public void unsetLife() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LIFE_ISSET_ID);
  }

  /** Returns true if field life is set (has been assigned a value) and false otherwise */
  public boolean isSetLife() {
    return EncodingUtils.testBit(__isset_bitfield, __LIFE_ISSET_ID);
  }

  public void setLifeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LIFE_ISSET_ID, value);
  }

  public int getArea() {
    return this.area;
  }

  public Player setArea(int area) {
    this.area = area;
    setAreaIsSet(true);
    return this;
  }

  public void unsetArea() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __AREA_ISSET_ID);
  }

  /** Returns true if field area is set (has been assigned a value) and false otherwise */
  public boolean isSetArea() {
    return EncodingUtils.testBit(__isset_bitfield, __AREA_ISSET_ID);
  }

  public void setAreaIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __AREA_ISSET_ID, value);
  }

  public long getLast_saved() {
    return this.last_saved;
  }

  public Player setLast_saved(long last_saved) {
    this.last_saved = last_saved;
    setLast_savedIsSet(true);
    return this;
  }

  public void unsetLast_saved() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LAST_SAVED_ISSET_ID);
  }

  /** Returns true if field last_saved is set (has been assigned a value) and false otherwise */
  public boolean isSetLast_saved() {
    return EncodingUtils.testBit(__isset_bitfield, __LAST_SAVED_ISSET_ID);
  }

  public void setLast_savedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LAST_SAVED_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case POSITION:
      if (value == null) {
        unsetPosition();
      } else {
        setPosition((List<Integer>)value);
      }
      break;

    case LIFE:
      if (value == null) {
        unsetLife();
      } else {
        setLife((Integer)value);
      }
      break;

    case AREA:
      if (value == null) {
        unsetArea();
      } else {
        setArea((Integer)value);
      }
      break;

    case LAST_SAVED:
      if (value == null) {
        unsetLast_saved();
      } else {
        setLast_saved((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME:
      return getName();

    case POSITION:
      return getPosition();

    case LIFE:
      return Integer.valueOf(getLife());

    case AREA:
      return Integer.valueOf(getArea());

    case LAST_SAVED:
      return Long.valueOf(getLast_saved());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NAME:
      return isSetName();
    case POSITION:
      return isSetPosition();
    case LIFE:
      return isSetLife();
    case AREA:
      return isSetArea();
    case LAST_SAVED:
      return isSetLast_saved();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Player)
      return this.equals((Player)that);
    return false;
  }

  public boolean equals(Player that) {
    if (that == null)
      return false;

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_position = true && this.isSetPosition();
    boolean that_present_position = true && that.isSetPosition();
    if (this_present_position || that_present_position) {
      if (!(this_present_position && that_present_position))
        return false;
      if (!this.position.equals(that.position))
        return false;
    }

    boolean this_present_life = true;
    boolean that_present_life = true;
    if (this_present_life || that_present_life) {
      if (!(this_present_life && that_present_life))
        return false;
      if (this.life != that.life)
        return false;
    }

    boolean this_present_area = true;
    boolean that_present_area = true;
    if (this_present_area || that_present_area) {
      if (!(this_present_area && that_present_area))
        return false;
      if (this.area != that.area)
        return false;
    }

    boolean this_present_last_saved = true;
    boolean that_present_last_saved = true;
    if (this_present_last_saved || that_present_last_saved) {
      if (!(this_present_last_saved && that_present_last_saved))
        return false;
      if (this.last_saved != that.last_saved)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_name = true && (isSetName());
    list.add(present_name);
    if (present_name)
      list.add(name);

    boolean present_position = true && (isSetPosition());
    list.add(present_position);
    if (present_position)
      list.add(position);

    boolean present_life = true;
    list.add(present_life);
    if (present_life)
      list.add(life);

    boolean present_area = true;
    list.add(present_area);
    if (present_area)
      list.add(area);

    boolean present_last_saved = true;
    list.add(present_last_saved);
    if (present_last_saved)
      list.add(last_saved);

    return list.hashCode();
  }

  @Override
  public int compareTo(Player other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPosition()).compareTo(other.isSetPosition());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPosition()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.position, other.position);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLife()).compareTo(other.isSetLife());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLife()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.life, other.life);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetArea()).compareTo(other.isSetArea());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetArea()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.area, other.area);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLast_saved()).compareTo(other.isSetLast_saved());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLast_saved()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.last_saved, other.last_saved);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Player(");
    boolean first = true;

    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("position:");
    if (this.position == null) {
      sb.append("null");
    } else {
      sb.append(this.position);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("life:");
    sb.append(this.life);
    first = false;
    if (!first) sb.append(", ");
    sb.append("area:");
    sb.append(this.area);
    first = false;
    if (!first) sb.append(", ");
    sb.append("last_saved:");
    sb.append(this.last_saved);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PlayerStandardSchemeFactory implements SchemeFactory {
    public PlayerStandardScheme getScheme() {
      return new PlayerStandardScheme();
    }
  }

  private static class PlayerStandardScheme extends StandardScheme<Player> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Player struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // POSITION
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.position = new ArrayList<Integer>(_list0.size);
                int _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readI32();
                  struct.position.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setPositionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // LIFE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.life = iprot.readI32();
              struct.setLifeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // AREA
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.area = iprot.readI32();
              struct.setAreaIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // LAST_SAVED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.last_saved = iprot.readI64();
              struct.setLast_savedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Player struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      if (struct.position != null) {
        oprot.writeFieldBegin(POSITION_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, struct.position.size()));
          for (int _iter3 : struct.position)
          {
            oprot.writeI32(_iter3);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(LIFE_FIELD_DESC);
      oprot.writeI32(struct.life);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(AREA_FIELD_DESC);
      oprot.writeI32(struct.area);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LAST_SAVED_FIELD_DESC);
      oprot.writeI64(struct.last_saved);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PlayerTupleSchemeFactory implements SchemeFactory {
    public PlayerTupleScheme getScheme() {
      return new PlayerTupleScheme();
    }
  }

  private static class PlayerTupleScheme extends TupleScheme<Player> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Player struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetName()) {
        optionals.set(0);
      }
      if (struct.isSetPosition()) {
        optionals.set(1);
      }
      if (struct.isSetLife()) {
        optionals.set(2);
      }
      if (struct.isSetArea()) {
        optionals.set(3);
      }
      if (struct.isSetLast_saved()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetPosition()) {
        {
          oprot.writeI32(struct.position.size());
          for (int _iter4 : struct.position)
          {
            oprot.writeI32(_iter4);
          }
        }
      }
      if (struct.isSetLife()) {
        oprot.writeI32(struct.life);
      }
      if (struct.isSetArea()) {
        oprot.writeI32(struct.area);
      }
      if (struct.isSetLast_saved()) {
        oprot.writeI64(struct.last_saved);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Player struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.position = new ArrayList<Integer>(_list5.size);
          int _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = iprot.readI32();
            struct.position.add(_elem6);
          }
        }
        struct.setPositionIsSet(true);
      }
      if (incoming.get(2)) {
        struct.life = iprot.readI32();
        struct.setLifeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.area = iprot.readI32();
        struct.setAreaIsSet(true);
      }
      if (incoming.get(4)) {
        struct.last_saved = iprot.readI64();
        struct.setLast_savedIsSet(true);
      }
    }
  }

}

