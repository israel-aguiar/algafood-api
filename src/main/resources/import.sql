insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Japonesa');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.5, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);

insert into estado (id, nome) values (1, 'Bahia');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Rio de Janeiro');

insert into cidade (id, nome, estado_id) values (1, 'Vitória da Conquista', 1);
insert into cidade (id, nome, estado_id) values (2, 'Itambé', 1);
insert into cidade (id, nome, estado_id) values (3, 'Sorocaba', 2);
insert into cidade (id, nome, estado_id) values (4, 'Osasco', 2);

insert into forma_pagamento (descricao) values ('À vista');
insert into forma_pagamento (descricao) values ('À prazo');

insert into permissao (nome, descricao) values ('Administrativa', 'permissão administrativa');
insert into permissao (nome, descricao) values ('Cliente', 'usuários clientes');