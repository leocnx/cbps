DROP TRIGGER IF EXISTS `set_rate_creation_date`;
DELIMITER ;;
CREATE TRIGGER `set_rate_creation_date` BEFORE INSERT ON `rate` FOR EACH ROW set new.creationdate = now()
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `set_rate_update_date`;
DELIMITER ;;
CREATE TRIGGER `set_rate_update_date` BEFORE UPDATE ON `rate` FOR EACH ROW set new.updatedate = now()
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `set_rate_item_creation_date`;
DELIMITER ;;
CREATE TRIGGER `set_rate_item_creation_date` BEFORE INSERT ON `rate_item` FOR EACH ROW set new.creationdate = now()
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `set_rate_item_update_date`;
DELIMITER ;;
CREATE TRIGGER `set_rate_item_update_date` BEFORE UPDATE ON `rate_item` FOR EACH ROW set new.updatedate = now()
;;
DELIMITER ;