INSERT INTO `transaction` (`id`, `created_date`, `modified_date`, `amount`, `description`, `type`) VALUES (1,'2020-03-17 21:04:22',NULL,10000,'Membeli kopi','PURCHASING')
INSERT INTO `transaction` (`id`, `created_date`, `modified_date`, `amount`, `description`, `type`) VALUES (2,'2020-03-17 21:05:27',NULL,20000,'Menjual kopi','SELLING')
INSERT INTO `transaction` (`id`, `created_date`, `modified_date`, `amount`, `description`, `type`) VALUES (3,'2020-03-17 21:05:48',NULL,15000,'Membeli kopi','PURCHASING')
INSERT INTO `transaction` (`id`, `created_date`, `modified_date`, `amount`, `description`, `type`) VALUES (4,'2020-03-17 21:06:11',NULL,100000,'Menjual baju','SELLING')
INSERT INTO `transaction` (`id`, `created_date`, `modified_date`, `amount`, `description`, `type`) VALUES (5,'2020-03-17 21:06:33',NULL,50000,'Membeli baju','PURCHASING');

INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (1,'2020-03-04 14:47:57.580431',NULL,'Beras');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (2,'2020-03-04 14:47:57.620933',NULL,'Kopi Hitam');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (3,'2020-03-04 14:47:57.641503',NULL,'Mi Instan');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (4,'2020-03-04 14:47:57.655285',NULL,'Mi Goreng Instan');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (5,'2020-03-04 14:47:57.666252',NULL,'Minyak Goreng');

INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`, `description`) VALUES (1,'2020-03-04 14:47:57.678596',NULL,'kg','kilogram');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`, `description`) VALUES (2,'2020-03-04 14:47:57.692418',NULL,'g','gram');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`, `description`) VALUES (3,'2020-03-04 14:47:57.705075',NULL,'l','liter');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `name`, `description`) VALUES (4,'2020-03-04 14:47:57.717457',NULL,'pack','pack');

INSERT INTO `stock` (`id`, `created_date`, `modified_date`, `quantity`, `item_id`, `unit_id`) VALUES (1,'2020-03-04 14:47:57.729164',NULL,2,1,1);
INSERT INTO `stock` (`id`, `created_date`, `modified_date`, `quantity`, `item_id`, `unit_id`) VALUES (2,'2020-03-04 14:47:57.785268',NULL,500,2,2);
INSERT INTO `stock` (`id`, `created_date`, `modified_date`, `quantity`, `item_id`, `unit_id`) VALUES (3,'2020-03-04 14:47:57.816729',NULL,40,3,4);
INSERT INTO `stock` (`id`, `created_date`, `modified_date`, `quantity`, `item_id`, `unit_id`) VALUES (4,'2020-03-04 14:47:57.840408',NULL,40,4,4);
INSERT INTO `stock` (`id`, `created_date`, `modified_date`, `quantity`, `item_id`, `unit_id`) VALUES (5,'2020-03-04 14:47:57.862522',NULL,5,5,3);

