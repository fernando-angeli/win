INSERT INTO user (name, email, password) VALUES ('admin', 'admin@admin.com', '$2a$10$PDgIgmaw7ta35CQ9wMoYeuwvWQFgiI3Tx1wbFx6C/cjkX0BOwxQX6');

INSERT INTO role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO role (authority) VALUES ('ROLE_OPERATOR');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);