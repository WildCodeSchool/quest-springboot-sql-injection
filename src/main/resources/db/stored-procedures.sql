-- This is an (relatively) UNSAFE way to get a customer's credit cards.
-- However, since we can only pass 6 characters as the identifier,
-- it doesn't leave much room for carrying out an SQL injection.
DELIMITER $$
CREATE PROCEDURE getCustomerCreditCards(
  IN customerIdentifier VARCHAR(6)
)
BEGIN
  SELECT cc.* FROM credit_card cc JOIN customer c ON cc.customer_id = c.id WHERE c.identifier = customerIdentifier;
END$$
DELIMITER ;

-- This is an UNSAFE way to get a customer by identifier and password.
-- It is vulnerable to SQL injection.
-- e.g. CALL unsafeGetCustomerByIdentifierAndPassword('797825', "' OR 1=1; -- '");
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
