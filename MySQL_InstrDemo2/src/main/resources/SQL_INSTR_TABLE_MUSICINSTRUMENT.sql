SELECT  instr_id, instr_name, muse_id, musician_name FROM instruments INNER JOIN musicians ON musicians.musician_instr=instruments.instr_id 
   WHERE muse_id=?
