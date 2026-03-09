meta:
  id: tcp_new_message
  endian: be
seq:
  - id: id
    type: str
    size: 3
    encoding: UTF-8
  - id: message_type
    type: u2
  - id: payload_length
    type: u2