INSERT INTO CUSTOMER_PROFILE (id, customer_id, name) 
VALUES (123, 'CUST0123', 'JOHN DOE'),
(124, 'CUST0124', 'JANE DOE'),
(156, 'CUST0156', 'UNCLE SAM');


INSERT INTO SERVICE (id, phone_number, activated, customer_profile_id)
VALUES (567, '0123456789', true, 123),
 (579, '1234567890', false, 123),
 (568, '2345678901', true, 123),
 (569, '3456789012', true, 124),
 (570, '4567890123', false, 124),
 (571, '5678901234', true, 124),
 (572, '6789012345', true, 124),
 (573, '7890123456', true, 156),
 (574, '8901234567', true, 156),
 (575, '9012345678', false, 156),
 (576, '1123456789', true, 156),
 (577, '2123456789', true, 124),
 (578, '3123456789', true, 156),
 (580, '4123456789', true, 123),
 (581, '5123456789', true, 123);