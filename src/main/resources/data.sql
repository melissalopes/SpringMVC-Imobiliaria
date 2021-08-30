INSERT INTO imob_malv.categoria (nome_categoria) VALUES
  ('Casa'),
  ('Apartamento'),
  ('Loft'),
  ('Kitnet'),
  ('Flat');
  
INSERT INTO imob_malv.negocio (nome_negocio) VALUES
  ('Compra'),
  ('Aluguel'),
  ('Financiamento'),
  ('Consórcio');
  
INSERT INTO imob_malv.estado (nome_estado, uf) VALUES
  ('São Paulo', 'SP'),
  ('Pernambuco', 'PE'),
  ('Rio de Janeiro', 'RJ'),
  ('Ceará', 'CE'),
  ('Bahia', 'BA');
  
INSERT INTO imob_malv.municipio (nome_municipio, estado) VALUES
  ('Mauá', 1),
  ('Olinda', 2),
  ('Valença', 3),
  ('Aquiraz', 4),
  ('Camaçari', 5);

  
INSERT INTO imob_malv.bairro (nome_bairro, municipio) VALUES
  ('Alto Boa Vista', 1),
  ('Aguazinha', 2),
  ('Benfica', 3),
  ('Ator Alegre', 4),
  ('Abrantes', 5);
  
INSERT INTO imob_malv.imovel (qtd_quartos, categoria, negocio, estado, municipio, bairro) VALUES
  (1,1,1,4,4,4),
  (4,2,2,1,1,1),
  (1,5,2,3,3,3),
  (2,3,3,1,1,1),
  (5,1,4,5,5,5);
  
