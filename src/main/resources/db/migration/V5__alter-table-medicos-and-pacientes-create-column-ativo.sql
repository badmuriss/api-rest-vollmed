alter table medicos add ativo tinyint;
UPDATE medicos SET ativo=1;
ALTER TABLE medicos
MODIFY COLUMN ativo tinyint NOT NULL;
alter table pacientes add ativo tinyint;
UPDATE pacientes SET ativo=1;
ALTER TABLE pacientes
MODIFY COLUMN ativo tinyint NOT NULL;