-- Adicionar Usuários do tipo ADMIN
INSERT INTO users (id, name, email, password, cpf, phone, profession, description, birth_date, role, created_at, updated_at) VALUES ('c7b3ef06-484e-4dc0-bd34-d1c7a0a4c376', 'Rafael Augusto', 'rafael.augusto@example.com', '$2a$10$tsM4szPxW7ZbSJbOyy4XL.o4d4m.a6w/Np0LyLA8lbnhpxHLkw5qK', '11122233344', '(48) 99901-0353', 'Software Developer', 'Experienced developer specializing in Java and Spring.', '1995-06-15', 'ADMIN', NOW(), NOW());
INSERT INTO users (id, name, email, password, cpf, phone, profession, description, birth_date, role, created_at, updated_at) VALUES ('003eced8-e3ae-4b24-b8d4-80cfc125151c', 'Alice Silva', 'alice.silva@example.com', '$2a$10$tsM4szPxW7ZbSJbOyy4XL.o4d4m.a6w/Np0LyLA8lbnhpxHLkw5qK', '12345678901', '(11) 98765-4321', 'Desenvolvedora', 'Desenvolvedora de software com 5 anos de experiência.', '1990-01-15', 'ADMIN', NOW(), NOW());
INSERT INTO users (id, name, email, password, cpf, phone, profession, description, birth_date, role, created_at, updated_at) VALUES ('a96253d5-424d-4f06-aa7d-dc6bf3002166', 'Bruno Costa', 'bruno.costa@example.com', '$2a$10$tsM4szPxW7ZbSJbOyy4XL.o4d4m.a6w/Np0LyLA8lbnhpxHLkw5qK', '23456789012', '(21) 98876-5432', 'Designer', 'Designer gráfico apaixonado por arte.', '1988-05-22', 'ADMIN', NOW(), NOW());
INSERT INTO users (id, name, email, password, cpf, phone, profession, description, birth_date, role, created_at, updated_at) VALUES ('317201ee-0bc6-45f1-bbfc-058f2d2a318a', 'Carla Oliveira', 'carla.oliveira@example.com', '$2a$10$tsM4szPxW7ZbSJbOyy4XL.o4d4m.a6w/Np0LyLA8lbnhpxHLkw5qK', '34567890123', '(31) 98765-1234', 'Gerente de Projetos', 'Gerente de projetos com foco em eficiência.', '1992-07-30', 'ADMIN', NOW(), NOW());

-- Adicionar usuários do tipo CUSTOMER
INSERT INTO users (id, name, email, password, cpf, phone, profession, description, birth_date, role, created_at, updated_at) VALUES ('fec2a3d3-350c-49d8-9c9c-47f5944800de', 'Daniel Pereira', 'daniel.pereira@example.com', '$2a$10$tsM4szPxW7ZbSJbOyy4XL.o4d4m.a6w/Np0LyLA8lbnhpxHLkw5qK', '45678901234', '(41) 98876-4321', 'Analista de Sistemas', 'Analista de sistemas com experiência em gestão.', '1995-02-18', 'CUSTOMER', NOW(), NOW());
INSERT INTO users (id, name, email, password, cpf, phone, profession, description, birth_date, role, created_at, updated_at) VALUES ('10caa2d5-22d9-4d4a-82d6-824ec304e288', 'Eduarda Santos', 'eduarda.santos@example.com', '$2a$10$tsM4szPxW7ZbSJbOyy4XL.o4d4m.a6w/Np0LyLA8lbnhpxHLkw5qK', '56789012345', '(51) 98765-6789', 'Engenheira', 'Engenheira civil apaixonada por construção.', '1985-11-05', 'CUSTOMER', NOW(), NOW());
INSERT INTO users (id, name, email, password, cpf, phone, profession, description, birth_date, role, created_at, updated_at) VALUES ('50c47e87-873a-4d82-8351-2b041123460a', 'Felipe Almeida', 'felipe.almeida@example.com', '$2a$10$tsM4szPxW7ZbSJbOyy4XL.o4d4m.a6w/Np0LyLA8lbnhpxHLkw5qK', '67890123456', '(61) 98876-5431', 'Consultor', 'Consultor de negócios com 10 anos de experiência.', '1990-03-12', 'CUSTOMER', NOW(), NOW());
INSERT INTO users (id, name, email, password, cpf, phone, profession, description, birth_date, role, created_at, updated_at) VALUES ('96c443ba-3336-44d0-b874-b03e87909cb0', 'Gisele Martins', 'gisele.martins@example.com', '$2a$10$tsM4szPxW7ZbSJbOyy4XL.o4d4m.a6w/Np0LyLA8lbnhpxHLkw5qK', '78901234567', '(71) 98765-4322', 'Fotógrafa', 'Fotógrafa freelance com foco em retratos.', '1994-09-19', 'CUSTOMER', NOW(), NOW());
INSERT INTO users (id, name, email, password, cpf, phone, profession, description, birth_date, role, created_at, updated_at) VALUES ('d7062553-0a15-4ae1-9030-ab043c5c5a3c', 'Henrique Lima', 'henrique.lima@example.com', '$2a$10$tsM4szPxW7ZbSJbOyy4XL.o4d4m.a6w/Np0LyLA8lbnhpxHLkw5qK', '89012345678', '(81) 98876-5433', 'Arquiteto', 'Arquiteto com paixão por design sustentável.', '1982-12-01', 'CUSTOMER', NOW(), NOW());
INSERT INTO users (id, name, email, password, cpf, phone, profession, description, birth_date, role, created_at, updated_at) VALUES ('5868790a-1789-4ad8-95ef-7cc8b5419ff4', 'Isabela Rocha', 'isabela.rocha@example.com', '$2a$10$tsM4szPxW7ZbSJbOyy4XL.o4d4m.a6w/Np0LyLA8lbnhpxHLkw5qK', '90123456789', '(91) 98765-1235', 'Médica', 'Médica especializada em pediatria.', '1987-04-25', 'CUSTOMER', NOW(), NOW());
INSERT INTO users (id, name, email, password, cpf, phone, profession, description, birth_date, role, created_at, updated_at) VALUES ('19198073-7d10-4975-8e17-7c17e11c04de', 'João Ferreira', 'joao.ferreira@example.com', '$2a$10$tsM4szPxW7ZbSJbOyy4XL.o4d4m.a6w/Np0LyLA8lbnhpxHLkw5qK', '01234567890', '(41) 98876-4323', 'Professor', 'Professor de matemática com 15 anos de experiência.', '1980-06-14', 'CUSTOMER', NOW(), NOW());

-- Adicionar categorias
INSERT INTO category (id, name, description, icon_name) VALUES ('58d23991-7f10-4af3-8fcb-52a9a24697de', 'domésticos', 'Serviços relacionados a cuidados e manutenções em casa, como limpeza e organização.', 'LampFloor');
INSERT INTO category (id, name, description, icon_name) VALUES ('1246f8d1-5bdf-4b9c-b7e4-49e3afc55ba8', 'elétrico', 'Serviços de instalação e manutenção elétrica para residências e empresas.', 'Bolt');
INSERT INTO category (id, name, description, icon_name) VALUES ('dbd56a8d-8731-4094-9be3-5e824e9f7387', 'manutenção', 'Serviços gerais de reparo e manutenção em diversos tipos de imóveis.', 'Wrench');
INSERT INTO category (id, name, description, icon_name) VALUES ('7b8dcde3-50a1-4098-9382-03b61f5479cd', 'administração', 'Serviços administrativos, como gestão de projetos e suporte ao cliente.', 'Briefcase');
INSERT INTO category (id, name, description, icon_name) VALUES ('47cb792b-75cf-470a-94fa-76be1a0298c4', 'consultoria', 'Consultoria em diversas áreas para ajudar a melhorar processos e estratégias.', 'User');
INSERT INTO category (id, name, description, icon_name) VALUES ('50ba3bb8-3b23-4d49-8f29-17af99c7403d', 'contabilidade', 'Serviços contábeis para gestão financeira, planejamento fiscal e mais.', 'Calculator');
INSERT INTO category (id, name, description, icon_name) VALUES ('cf6bc7cf-1c3a-4f85-ae80-6b90c20f7e95', 'design', 'Serviços de design gráfico, web design, criação de logotipos e muito mais.', 'Brush');
INSERT INTO category (id, name, description, icon_name) VALUES ('85c12d9e-0af9-4ad8-83a5-04677af15bde', 'engenharia', 'Serviços de engenharia civil, mecânica, elétrica e outros ramos da engenharia.', 'HardHat');
INSERT INTO category (id, name, description, icon_name) VALUES ('7d485a16-1ee9-4ff6-8fd4-cba3c9fe4a1d', 'Data Entry', 'Serviços de entrada de dados e organização de informações.', 'Scroll');
INSERT INTO category (id, name, description, icon_name) VALUES ('f1c9378e-b33c-4fd4-86bc-8bb8db82c318', 'Social Media Management', 'Gerenciamento de redes sociais e criação de conteúdo.', 'Megaphone');
INSERT INTO category (id, name, description, icon_name) VALUES ('e8b123a3-4a29-4d47-bb1f-01c43e78f0d5', 'jurídico', 'Serviços legais, como consultoria jurídica e representação em processos.', 'Gavel');
INSERT INTO category (id, name, description, icon_name) VALUES ('f3dcb593-5f36-4b4c-910f-ada5018c3e98', 'marketing', 'Serviços de marketing digital, criação de campanhas publicitárias e branding.', 'Megaphone');
INSERT INTO category (id, name, description, icon_name) VALUES ('4d5304c8-6f24-4d07-b844-6f6b6cf5f6c5', 'saúde', 'Serviços de cuidados com a saúde, como atendimento médico, fisioterapia e mais.', 'Heart');
INSERT INTO category (id, name, description, icon_name) VALUES ('de0f1b7c-5317-4b1f-8b1f-4cb23980d7c0', 'tecnologia', 'Serviços de TI, desenvolvimento de software, suporte técnico e mais.', 'Monitor');
INSERT INTO category (id, name, description, icon_name) VALUES ('e60b9c6e-77cc-4b53-9316-b003f005a69e', 'transporte', 'Serviços de transporte e logística, incluindo entregas e mudanças.', 'Truck');
INSERT INTO category (id, name, description, icon_name) VALUES ('dbfdf6fa-34d5-49f8-9f6c-4b31a5dc57ef', 'vendas', 'Serviços relacionados a vendas, desde vendedores a representantes comerciais.', 'BadgeDollarSign');
INSERT INTO category (id, name, description, icon_name) VALUES ('a49e4f41-4f7f-49cb-b38c-47620894b509', 'fotografia', 'Serviços de fotografia para eventos, retratos, produtos e mais.', 'Camera');
INSERT INTO category (id, name, description, icon_name) VALUES ('f79e44d4-2f53-4cb5-9332-62902af2d9e8', 'música', 'Serviços musicais, como aulas de instrumentos, produção musical e mais.', 'Music');
INSERT INTO category (id, name, description, icon_name) VALUES ('6a6d98c3-45c1-4f64-9e3f-3e6d43c3f6c6', 'educação', 'Serviços educacionais, como tutoria, ensino e formação acadêmica.', 'BookOpen');
INSERT INTO category (id, name, description, icon_name) VALUES ('bf34c6b0-5dcb-4c2d-8d2c-e90950e6e91f', 'idiomas', 'Serviços de ensino de idiomas, tradução e interpretação.', 'Languages');
INSERT INTO category (id, name, description, icon_name) VALUES ('67c48c73-c8d5-4c8e-b60e-1f79e2ac7ae6', 'esportes', 'Serviços relacionados a esportes, como treinamentos, personal trainers e mais.', 'Dumbbell');
INSERT INTO category (id, name, description, icon_name) VALUES ('2d7e5ff8-85b0-4b79-b1f8-150f54550ee8', 'culinária', 'Serviços culinários, como chefs particulares, aulas de culinária e mais.', 'Utensils');
INSERT INTO category (id, name, description, icon_name) VALUES ('b14cdca0-48d4-4264-b13c-b658c92f9d0b', 'beleza', 'Serviços de beleza, como cabeleireiros, maquiadores e estética em geral.', 'Scissors');
INSERT INTO category (id, name, description, icon_name) VALUES ('3ebc5b32-73e8-4d7e-b44d-19d5730a9642', 'moda', 'Serviços de moda, como consultoria de estilo, design de roupas e mais.', 'Shirt');
INSERT INTO category (id, name, description, icon_name) VALUES ('5ab1ef10-65c0-4b98-b2b3-e305c8de9e43', 'artesanato', 'Serviços de artesanato, incluindo criação de itens personalizados e decoração.', 'Scroll');

-- Criar o serviço do usuário CARLA OLIVEIRA: id 317201ee-0bc6-45f1-bbfc-058f2d2a318a
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('d1e8a4b1-0b87-4d69-b9f4-df88b6005b67', 'Reforma de Escritório', 'Reforma completa do espaço de trabalho.', '5000-6000', 'ONSITE', 'São Paulo, SP', '2024-12-01', 'OPEN', '317201ee-0bc6-45f1-bbfc-058f2d2a318a', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('f2b72f3d-0f02-4b47-8a3c-b6c3d7c2f5e4', 'Manutenção de Rede', 'Instalação e configuração de rede no escritório.', '1500-3000', 'ONSITE', 'São Paulo, SP', '2024-11-15', 'OPEN', '317201ee-0bc6-45f1-bbfc-058f2d2a318a', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('3cb8c82c-ec06-43b3-8e3a-1f9d1f4d90f7', 'Consultoria de Ergonomia', 'Avaliação e melhorias na ergonomia do espaço de trabalho.', '800', 'REMOTE', 'São Paulo, SP', '2024-10-30', 'OPEN', '317201ee-0bc6-45f1-bbfc-058f2d2a318a', NOW());

-- Criar o serviço do usuário FELIPE ALMEIDA: id 50c47e87-873a-4d82-8351-2b041123460a
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('7c9c0c26-fc77-48f6-8729-754d5f3e5715', 'Reforma de Escritório', 'Reforma completa do espaço de trabalho.', '3000-6000', 'REMOTE', 'Criciúma, SC', '2024-12-01', 'OPEN', '50c47e87-873a-4d82-8351-2b041123460a', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('d6c8c60e-5d5c-4b3d-bfd9-c34c6ef06249', 'Criação de Site', 'Desenvolvimento de site institucional para a empresa.', '2500-3000', 'REMOTE', 'Criciúma, SC', '2024-11-10', 'OPEN', '50c47e87-873a-4d82-8351-2b041123460a', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('1e8b83b9-8c82-45ab-bd09-43bfa1eb153b', 'Assessoria Contábil', 'Serviço de contabilidade mensal.', '1200', 'REMOTE', 'Criciúma, SC', '2024-11-20', 'OPEN', '50c47e87-873a-4d82-8351-2b041123460a', NOW());

-- Criar o serviço do usuário HENRIQUE LIMA: id d7062553-0a15-4ae1-9030-ab043c5c5a3c
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('1e4d120c-26e7-4e8c-88f7-27b314de8b2e', 'Reforma de Escritório', 'Reforma completa do espaço de trabalho.', '2000-6000', 'REMOTE', 'Florianópolis, SC', '2024-12-01', 'OPEN', 'd7062553-0a15-4ae1-9030-ab043c5c5a3c', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('c5f1b298-3e79-4e3e-b81a-bd50f8a5b24b', 'Reforma de Banheiro', 'Reforma completa do banheiro do escritório.', '3000-4000', 'REMOTE', 'Florianópolis, SC', '2024-11-25', 'OPEN', 'd7062553-0a15-4ae1-9030-ab043c5c5a3c', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('70d5f370-4c14-4318-9516-38f1f99cc6b9', 'Instalação de Ar Condicionado', 'Instalação de ar condicionado para o ambiente de trabalho.', '2500-3000', 'ONSITE', 'Florianópolis, SC', '2024-12-05', 'OPEN', 'd7062553-0a15-4ae1-9030-ab043c5c5a3c', NOW());

-- Criar o serviço do usuário JOÃO FERREIRA: id 19198073-7d10-4975-8e17-7c17e11c04de
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('28f34c26-93c3-4f39-bd1f-bc6b61f4f768', 'Reforma de Escritório', 'Reforma completa do espaço de trabalho.', '1000-2000', 'ONSITE', 'Itaquaquecetuba, SP', '2024-12-01', 'OPEN', '19198073-7d10-4975-8e17-7c17e11c04de', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('8e3de7bc-f8cc-48b6-a54d-30e1e7b3f9b7', 'Desenvolvimento de Software', 'Desenvolvimento de software personalizado para o escritório.', '4000-5000', 'REMOTE', 'Itaquaquecetuba, SP', '2024-11-15', 'OPEN', '19198073-7d10-4975-8e17-7c17e11c04de', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('f84f1f59-f1c1-4f63-b4de-7cbe3252d68e', 'Treinamento de Equipe', 'Treinamento de equipe sobre novas ferramentas de trabalho.', '1500-4000', 'ONSITE', 'Itaquaquecetuba, SP', '2024-12-01', 'OPEN', '19198073-7d10-4975-8e17-7c17e11c04de', NOW());

-- Criar o serviço do usuário ALICE SILVA: id 003eced8-e3ae-4b24-b8d4-80cfc125151c
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('5bc45120-31e1-4c72-ae62-b7f4c9ff646e', 'Reforma de Escritório', 'Reforma completa do espaço de trabalho.', '4000-7000', 'REMOTE', 'São Paulo, SP', '2024-12-01', 'OPEN', '003eced8-e3ae-4b24-b8d4-80cfc125151c', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('d3e8c527-0c94-4f44-8055-512e8c57c6dc', 'Decoração de Escritório', 'Decoração e ambientação do espaço de trabalho.', '2500-3500', 'REMOTE', 'São Paulo, SP', '2024-11-30', 'OPEN', '003eced8-e3ae-4b24-b8d4-80cfc125151c', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('e1d0b0b7-bf91-473b-8bff-d4b473d77b19', 'Suporte Técnico', 'Suporte técnico para computadores e equipamentos.', '500-1500', 'ONSITE', 'São Paulo, SP', '2024-11-05', 'OPEN', '003eced8-e3ae-4b24-b8d4-80cfc125151c', NOW());

-- Criar o serviço do usuário RAFAEL AUGUSTO: id c7b3ef06-484e-4dc0-bd34-d1c7a0a4c376
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('4e2f1b42-3b43-4e4e-9c3b-1d2a4d6b76a9', 'Limpar Apartamento', 'Limpeza completa no apartamento.', '200-400', 'ONSITE', 'Criciúma, SC', '2024-10-15', 'OPEN', 'c7b3ef06-484e-4dc0-bd34-d1c7a0a4c376', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('a9b4a2e7-f66c-4c89-88f2-59d66fefb6cb', 'Instalação de Piso', 'Instalação de piso laminado no escritório.', '1800-2000', 'ONSITE', 'Criciúma, SC', '2024-11-15', 'OPEN', 'c7b3ef06-484e-4dc0-bd34-d1c7a0a4c376', NOW());
INSERT INTO service (id, title, description, budget, work_type, location, deadline, status, client_id, created_at) VALUES ('e6e1d5c3-0ed9-4478-aacd-bdfc3b0deaf2', 'Reforma de Fachada', 'Reforma da fachada do prédio comercial.', '4000-6000', 'ONSITE', 'Itajaí, SC', '2024-12-10', 'OPEN', 'c7b3ef06-484e-4dc0-bd34-d1c7a0a4c376', NOW());

-- Serviços do cliente ID 317201ee-0bc6-45f1-bbfc-058f2d2a318a
INSERT INTO category_service (category_id, service_id) VALUES ('dbd56a8d-8731-4094-9be3-5e824e9f7387', 'd1e8a4b1-0b87-4d69-b9f4-df88b6005b67'); -- Manutenção
INSERT INTO category_service (category_id, service_id) VALUES ('de0f1b7c-5317-4b1f-8b1f-4cb23980d7c0', 'd1e8a4b1-0b87-4d69-b9f4-df88b6005b67'); -- Tecnologia
INSERT INTO category_service (category_id, service_id) VALUES ('47cb792b-75cf-470a-94fa-76be1a0298c4', 'd1e8a4b1-0b87-4d69-b9f4-df88b6005b67'); -- Consultoria

INSERT INTO category_service (category_id, service_id) VALUES ('de0f1b7c-5317-4b1f-8b1f-4cb23980d7c0', 'f2b72f3d-0f02-4b47-8a3c-b6c3d7c2f5e4'); -- Tecnologia
INSERT INTO category_service (category_id, service_id) VALUES ('cf6bc7cf-1c3a-4f85-ae80-6b90c20f7e95', 'f2b72f3d-0f02-4b47-8a3c-b6c3d7c2f5e4'); -- Design
INSERT INTO category_service (category_id, service_id) VALUES ('dbd56a8d-8731-4094-9be3-5e824e9f7387', 'f2b72f3d-0f02-4b47-8a3c-b6c3d7c2f5e4'); -- Manutenção

INSERT INTO category_service (category_id, service_id) VALUES ('47cb792b-75cf-470a-94fa-76be1a0298c4', '3cb8c82c-ec06-43b3-8e3a-1f9d1f4d90f7'); -- Consultoria
INSERT INTO category_service (category_id, service_id) VALUES ('85c12d9e-0af9-4ad8-83a5-04677af15bde', '3cb8c82c-ec06-43b3-8e3a-1f9d1f4d90f7'); -- Engenharia
INSERT INTO category_service (category_id, service_id) VALUES ('b14cdca0-48d4-4264-b13c-b658c92f9d0b', '3cb8c82c-ec06-43b3-8e3a-1f9d1f4d90f7'); -- Beleza

-- Serviços do cliente ID 50c47e87-873a-4d82-8351-2b041123460a
INSERT INTO category_service (category_id, service_id) VALUES ('dbd56a8d-8731-4094-9be3-5e824e9f7387', '7c9c0c26-fc77-48f6-8729-754d5f3e5715'); -- Manutenção
INSERT INTO category_service (category_id, service_id) VALUES ('de0f1b7c-5317-4b1f-8b1f-4cb23980d7c0', '7c9c0c26-fc77-48f6-8729-754d5f3e5715'); -- Tecnologia
INSERT INTO category_service (category_id, service_id) VALUES ('85c12d9e-0af9-4ad8-83a5-04677af15bde', '7c9c0c26-fc77-48f6-8729-754d5f3e5715'); -- Engenharia

INSERT INTO category_service (category_id, service_id) VALUES ('de0f1b7c-5317-4b1f-8b1f-4cb23980d7c0', 'd6c8c60e-5d5c-4b3d-bfd9-c34c6ef06249'); -- Tecnologia
INSERT INTO category_service (category_id, service_id) VALUES ('f3dcb593-5f36-4b4c-910f-ada5018c3e98', 'd6c8c60e-5d5c-4b3d-bfd9-c34c6ef06249'); -- Marketing
INSERT INTO category_service (category_id, service_id) VALUES ('cf6bc7cf-1c3a-4f85-ae80-6b90c20f7e95', 'd6c8c60e-5d5c-4b3d-bfd9-c34c6ef06249'); -- Design

INSERT INTO category_service (category_id, service_id) VALUES ('50ba3bb8-3b23-4d49-8f29-17af99c7403d', '1e8b83b9-8c82-45ab-bd09-43bfa1eb153b'); -- Contabilidade
INSERT INTO category_service (category_id, service_id) VALUES ('7b8dcde3-50a1-4098-9382-03b61f5479cd', '1e8b83b9-8c82-45ab-bd09-43bfa1eb153b'); -- Administração
INSERT INTO category_service (category_id, service_id) VALUES ('47cb792b-75cf-470a-94fa-76be1a0298c4', '1e8b83b9-8c82-45ab-bd09-43bfa1eb153b'); -- Consultoria

-- Serviços do cliente ID d7062553-0a15-4ae1-9030-ab043c5c5a3c
INSERT INTO category_service (category_id, service_id) VALUES ('dbd56a8d-8731-4094-9be3-5e824e9f7387', '1e4d120c-26e7-4e8c-88f7-27b314de8b2e'); -- Manutenção
INSERT INTO category_service (category_id, service_id) VALUES ('85c12d9e-0af9-4ad8-83a5-04677af15bde', '1e4d120c-26e7-4e8c-88f7-27b314de8b2e'); -- Engenharia
INSERT INTO category_service (category_id, service_id) VALUES ('de0f1b7c-5317-4b1f-8b1f-4cb23980d7c0', '1e4d120c-26e7-4e8c-88f7-27b314de8b2e'); -- Tecnologia

INSERT INTO category_service (category_id, service_id) VALUES ('dbd56a8d-8731-4094-9be3-5e824e9f7387', 'c5f1b298-3e79-4e3e-b81a-bd50f8a5b24b'); -- Manutenção
INSERT INTO category_service (category_id, service_id) VALUES ('85c12d9e-0af9-4ad8-83a5-04677af15bde', 'c5f1b298-3e79-4e3e-b81a-bd50f8a5b24b'); -- Engenharia
INSERT INTO category_service (category_id, service_id) VALUES ('de0f1b7c-5317-4b1f-8b1f-4cb23980d7c0', 'c5f1b298-3e79-4e3e-b81a-bd50f8a5b24b'); -- Tecnologia

INSERT INTO category_service (category_id, service_id) VALUES ('dbd56a8d-8731-4094-9be3-5e824e9f7387', '28f34c26-93c3-4f39-bd1f-bc6b61f4f768'); -- Manutenção
INSERT INTO category_service (category_id, service_id) VALUES ('85c12d9e-0af9-4ad8-83a5-04677af15bde', '28f34c26-93c3-4f39-bd1f-bc6b61f4f768'); -- Engenharia
INSERT INTO category_service (category_id, service_id) VALUES ('de0f1b7c-5317-4b1f-8b1f-4cb23980d7c0', '28f34c26-93c3-4f39-bd1f-bc6b61f4f768'); -- Tecnologia

-- Serviços do cliente ID 317201ee-0bc6-45f1-bbfc-058f2d2a318a (continuando)
INSERT INTO category_service (category_id, service_id) VALUES ('7b8dcde3-50a1-4098-9382-03b61f5479cd', '8e3de7bc-f8cc-48b6-a54d-30e1e7b3f9b7'); -- Administração
INSERT INTO category_service (category_id, service_id) VALUES ('47cb792b-75cf-470a-94fa-76be1a0298c4', '8e3de7bc-f8cc-48b6-a54d-30e1e7b3f9b7'); -- Consultoria
INSERT INTO category_service (category_id, service_id) VALUES ('cf6bc7cf-1c3a-4f85-ae80-6b90c20f7e95', '8e3de7bc-f8cc-48b6-a54d-30e1e7b3f9b7'); -- Design

INSERT INTO category_service (category_id, service_id) VALUES ('50ba3bb8-3b23-4d49-8f29-17af99c7403d', 'f84f1f59-f1c1-4f63-b4de-7cbe3252d68e'); -- Contabilidade
INSERT INTO category_service (category_id, service_id) VALUES ('85c12d9e-0af9-4ad8-83a5-04677af15bde', 'f84f1f59-f1c1-4f63-b4de-7cbe3252d68e'); -- Engenharia
INSERT INTO category_service (category_id, service_id) VALUES ('dbd56a8d-8731-4094-9be3-5e824e9f7387', 'f84f1f59-f1c1-4f63-b4de-7cbe3252d68e'); -- Manutenção

INSERT INTO category_service (category_id, service_id) VALUES ('cf6bc7cf-1c3a-4f85-ae80-6b90c20f7e95', '5bc45120-31e1-4c72-ae62-b7f4c9ff646e'); -- Design
INSERT INTO category_service (category_id, service_id) VALUES ('f3dcb593-5f36-4b4c-910f-ada5018c3e98', '5bc45120-31e1-4c72-ae62-b7f4c9ff646e'); -- Marketing
INSERT INTO category_service (category_id, service_id) VALUES ('7b8dcde3-50a1-4098-9382-03b61f5479cd', '5bc45120-31e1-4c72-ae62-b7f4c9ff646e'); -- Administração

-- Serviços do cliente ID 50c47e87-873a-4d82-8351-2b041123460a (continuando)
INSERT INTO category_service (category_id, service_id) VALUES ('50ba3bb8-3b23-4d49-8f29-17af99c7403d', 'd3e8c527-0c94-4f44-8055-512e8c57c6dc'); -- Contabilidade
INSERT INTO category_service (category_id, service_id) VALUES ('f3dcb593-5f36-4b4c-910f-ada5018c3e98', 'd3e8c527-0c94-4f44-8055-512e8c57c6dc'); -- Marketing
INSERT INTO category_service (category_id, service_id) VALUES ('85c12d9e-0af9-4ad8-83a5-04677af15bde', 'd3e8c527-0c94-4f44-8055-512e8c57c6dc'); -- Engenharia

INSERT INTO category_service (category_id, service_id) VALUES ('cf6bc7cf-1c3a-4f85-ae80-6b90c20f7e95', 'e1d0b0b7-bf91-473b-8bff-d4b473d77b19'); -- Design
INSERT INTO category_service (category_id, service_id) VALUES ('7b8dcde3-50a1-4098-9382-03b61f5479cd', 'e1d0b0b7-bf91-473b-8bff-d4b473d77b19'); -- Administração
INSERT INTO category_service (category_id, service_id) VALUES ('f3dcb593-5f36-4b4c-910f-ada5018c3e98', 'e1d0b0b7-bf91-473b-8bff-d4b473d77b19'); -- Marketing

-- Serviços do cliente ID d7062553-0a15-4ae1-9030-ab043c5c5a3c (continuando)
INSERT INTO category_service (category_id, service_id) VALUES ('cf6bc7cf-1c3a-4f85-ae80-6b90c20f7e95', '4e2f1b42-3b43-4e4e-9c3b-1d2a4d6b76a9'); -- Design
INSERT INTO category_service (category_id, service_id) VALUES ('47cb792b-75cf-470a-94fa-76be1a0298c4', '4e2f1b42-3b43-4e4e-9c3b-1d2a4d6b76a9'); -- Consultoria
INSERT INTO category_service (category_id, service_id) VALUES ('85c12d9e-0af9-4ad8-83a5-04677af15bde', '4e2f1b42-3b43-4e4e-9c3b-1d2a4d6b76a9'); -- Engenharia

INSERT INTO category_service (category_id, service_id) VALUES ('dbd56a8d-8731-4094-9be3-5e824e9f7387', 'a9b4a2e7-f66c-4c89-88f2-59d66fefb6cb'); -- Manutenção
INSERT INTO category_service (category_id, service_id) VALUES ('85c12d9e-0af9-4ad8-83a5-04677af15bde', 'a9b4a2e7-f66c-4c89-88f2-59d66fefb6cb'); -- Engenharia
INSERT INTO category_service (category_id, service_id) VALUES ('de0f1b7c-5317-4b1f-8b1f-4cb23980d7c0', 'a9b4a2e7-f66c-4c89-88f2-59d66fefb6cb'); -- Tecnologia

INSERT INTO category_service (category_id, service_id) VALUES ('f3dcb593-5f36-4b4c-910f-ada5018c3e98', 'e6e1d5c3-0ed9-4478-aacd-bdfc3b0deaf2'); -- Marketing
INSERT INTO category_service (category_id, service_id) VALUES ('7b8dcde3-50a1-4098-9382-03b61f5479cd', 'e6e1d5c3-0ed9-4478-aacd-bdfc3b0deaf2'); -- Administração
INSERT INTO category_service (category_id, service_id) VALUES ('50ba3bb8-3b23-4d49-8f29-17af99c7403d', 'e6e1d5c3-0ed9-4478-aacd-bdfc3b0deaf2'); -- Contabilidade

-- Adicionar endereço para usuário RAFAEL AUGUSTO

INSERT INTO address (id, state, city, neighborhood, street, postal_code, house_number, type, apartment_number, apartment_name, user_id) VALUES ('3b9c23b2-8c5c-4f85-9b5c-54b78f4b8e07', 'SC', 'Criciúma', 'Santa Bárbara', 'Argemiro Frutuoso', '88804100', 111, 'HOUSE', null, null, 'c7b3ef06-484e-4dc0-bd34-d1c7a0a4c376');
INSERT INTO address (id, state, city, neighborhood, street, postal_code, house_number, type, apartment_number, apartment_name, user_id) VALUES ('b4c7e6c0-0c3e-4a84-a5d6-4d3d7d0e831a', 'MG', 'Belo Horizonte', 'Savassi', 'Rua Tomé de Souza', '88810004', 850, 'HOUSE', null, null, 'c7b3ef06-484e-4dc0-bd34-d1c7a0a4c376');

