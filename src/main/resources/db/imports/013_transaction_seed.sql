-- Transações para a Wallet de Rafael Augusto
INSERT INTO transaction (id, amount, description, transaction_date, transaction_type, wallet_id)
VALUES ('db7e3f91-4c7a-4c7f-943b-72d9f6e1a3b5', 500.00, 'Depósito inicial', '2024-11-20T10:00:00', 'DEPOSIT', 'cb7e67b9-a1d3-4b62-bb9e-d5f8e84d1af7');

INSERT INTO transaction (id, amount, transaction_date, transaction_type, wallet_id)
VALUES ('3d2c4e71-6b3d-4c8e-b91f-7c2f8d7e5b43', 200.00,  '2024-11-21T14:30:00', 'WITHDRAWAL', 'cb7e67b9-a1d3-4b62-bb9e-d5f8e84d1af7');

-- Transações para a Wallet de Alice Silva
INSERT INTO transaction (id, amount, description, transaction_date, transaction_type, wallet_id)
VALUES ('a3b8c7e4-d92f-4c7d-b843-2f9d1c72a6f3', 1000.00, 'Transferência recebida', '2024-11-19T16:00:00', 'TRANSFER', '82e1a2d4-7f23-4417-bc8e-1d1d3174a561');

INSERT INTO transaction (id, amount, transaction_date, transaction_type, wallet_id)
VALUES ('7c5e8f93-a3b2-4d8f-b721-c7d6f2a8b934', 150.00, '2024-11-22T09:45:00', 'WITHDRAWAL', '82e1a2d4-7f23-4417-bc8e-1d1d3174a561');

-- Transações para a Wallet de Bruno Costa
INSERT INTO transaction (id, amount, description, transaction_date, transaction_type, wallet_id)
VALUES ('5b3d9f71-4c7a-43d2-b84e-7d9c2f1a6c83', 300.00, 'Depósito bancário', '2024-11-20T12:00:00', 'DEPOSIT', '5a2c4623-6e91-4031-a8f2-e5c9712d3d6b');

INSERT INTO transaction (id, amount, transaction_date, transaction_type, wallet_id)
VALUES ('2f8c7e5a-b9c3-47d2-b943-6c7a1f2d3e94', 100.00,  '2024-11-23T10:00:00', 'WITHDRAWAL', '5a2c4623-6e91-4031-a8f2-e5c9712d3d6b');

-- Transações para a Wallet de Daniel Pereira
INSERT INTO transaction (id, amount, description, transaction_date, transaction_type, wallet_id)
VALUES ('c5e7b91f-2f4d-4c71-8b93-5d8f3a7c9b24', 200.00, 'Depósito de reembolso', '2024-11-21T11:30:00', 'DEPOSIT', 'b271dcf9-8723-4b72-b0cc-2d97cfd2e8d1');

INSERT INTO transaction (id, amount, transaction_date, transaction_type, wallet_id)
VALUES ('d9e7c5b4-8f3d-4a2c-b71f-3f8d9e1a7c62', 50.00,  '2024-11-22T16:15:00', 'WITHDRAWAL', 'b271dcf9-8723-4b72-b0cc-2d97cfd2e8d1');
