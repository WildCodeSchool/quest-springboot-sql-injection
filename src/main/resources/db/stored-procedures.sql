-- Get a customer's credit cards.
DELIMITER $$
CREATE PROCEDURE getCustomerCreditCards(
  IN customerIdentifier VARCHAR(6)
)
BEGIN
  SELECT cc.* FROM credit_card cc JOIN customer c ON cc.customer_id = c.id WHERE c.identifier = customerIdentifier;
END$$
DELIMITER ;

-- Get a customer by identifier and password.
DELIMITER $$
CREATE PROCEDURE getCustomerByIdentifierAndPassword(
  IN customerIdentifier VARCHAR(6),
  IN customerPassword VARCHAR(64)
)
BEGIN
  SELECT * FROM customer WHERE identifier = customerIdentifier AND password = customerPassword;
END$$
DELIMITER ;

-- UPDATE a customer's profile.
-- This procedure allows us to group the UPDATE and the SELECT that follows.
DELIMITER $$
CREATE PROCEDURE updateCustomer(
  IN customerIdentifier VARCHAR(6),
  IN email VARCHAR(80),
  IN password VARCHAR(64)
)
BEGIN
  -- If password is not provided, update only email
  IF password = '' THEN
    UPDATE customer SET email = email WHERE identifier = customerIdentifier;
  -- Otherwise update both email and password
  ELSE
    UPDATE customer SET email = email, password = password WHERE identifier = customerIdentifier;
  END IF;
  SELECT * FROM customer WHERE identifier = customerIdentifier;
END$$
DELIMITER ;
