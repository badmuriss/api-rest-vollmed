UPDATE medicos SET telefone=id;
alter table medicos add constraint unique(telefone);