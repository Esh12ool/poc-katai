meta:
  id: sensor_data
  endian: be

enums:
  category_type:
    0: unknown
    1: temperature
    2: humidity
    3: pressure
    4: motion

seq:
  - id: flags
    type: flag_bits
types:
  flag_bits:
    seq:
      - id: is_active_bool
        type: b1    # 1 bit (boolean)
      - id: category
        type: b3
        enum: category_type
      - id: reserved
        type: b7    # 7 bits (padding to reach 1 byte)
      - id: sensor_value
        type: u1    # The next full byte

    #instances:
    #  is_active_int:
     # value: is_active_bool.to_i