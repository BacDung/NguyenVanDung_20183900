DROP DATABASE IF EXISTS aims_dungnv;
CREATE DATABASE aims_dungnv;
USE aims_dungnv; 

CREATE TABLE Media_DungNV(
  id_3900 INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  category_3900 VARCHAR(45) NOT NULL,
  price_3900 INTEGER NOT NULL,
  quantity_3900 INTEGER NOT NULL,
  title_3900 VARCHAR(45) NOT NULL,
  value_3900 INTEGER NOT NULL,
  imageUrl_3900 VARCHAR(45) NOT NULL
);
CREATE TABLE CD_DungNV(
  id_3900 INTEGER PRIMARY KEY NOT NULL,
  artist_3900 VARCHAR(45) NOT NULL,
  recordLabel_3900 VARCHAR(45) NOT NULL,
  musicType_3900 VARCHAR(45) NOT NULL,
  releasedDate_3900 DATE,
  CONSTRAINT fk_CD_Media1
    FOREIGN KEY(id_3900)
    REFERENCES Media_DungNV(id_3900)
);
CREATE TABLE Book_DungNV(
  id_3900 INTEGER PRIMARY KEY NOT NULL,
  author_3900 VARCHAR(45) NOT NULL,
  coverType_3900 VARCHAR(45) NOT NULL,
  publisher_3900 VARCHAR(45) NOT NULL,
  publishDate_3900 DATETIME NOT NULL,
  numOfPages_3900 INTEGER NOT NULL,
  language_3900 VARCHAR(45) NOT NULL,
  bookCategory_3900 VARCHAR(45) NOT NULL,
  CONSTRAINT fk_Book_Media1
    FOREIGN KEY(id_3900)
    REFERENCES Media_DungNV(id_3900)
);
CREATE TABLE DeleveryInfo_DungNV(
  id_3900 INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name_3900 VARCHAR(45),
  province_3900 VARCHAR(45),
  instructions_3900 VARCHAR(200),
  address_3900 VARCHAR(100)
);
CREATE TABLE Card_DungNV(
  id_3900 INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  cardCode_3900 VARCHAR(15) NOT NULL,
  owner_3900 VARCHAR(45) NOT NULL,
  cvvCode_3900 VARCHAR(3) NOT NULL,
  dateExpired_3900 VARCHAR(4) NOT NULL
);
CREATE TABLE DVD_DungNV(
  id_3900 INTEGER PRIMARY KEY NOT NULL,
  discType_3900 VARCHAR(45) NOT NULL,
  director_3900 VARCHAR(45) NOT NULL,
  runtime_3900 INTEGER NOT NULL,
  studio_3900 VARCHAR(45) NOT NULL,
  subtitle_3900 VARCHAR(45) NOT NULL,
  releasedDate_3900 DATETIME,
  CONSTRAINT fk_DVD_Media1
    FOREIGN KEY(id_3900)
    REFERENCES Media_DungNV(id_3900)
);
CREATE TABLE Order_DungNV(
  id_3900 INTEGER NOT NULL,
  shippingFees_3900 VARCHAR(45),
  deleveryInfoId_3900 INTEGER NOT NULL,
  PRIMARY KEY(id_3900,deleveryInfoId_3900),
  CONSTRAINT fk_Order_DeleveryInfo1
    FOREIGN KEY(deleveryInfoId_3900)
    REFERENCES DeleveryInfo_DungNV(id_3900)
);
CREATE INDEX aims_Order_fk_Order_DeleveryInfo1_idx ON Order_DungNV (deleveryInfoId_3900);
CREATE TABLE OrderMedia_DungNV(
  orderID_3900 INTEGER NOT NULL,
  price_3900 INTEGER NOT NULL,
  quantity_3900 INTEGER NOT NULL,
  mediaId_3900 INTEGER NOT NULL,
  PRIMARY KEY(orderID_3900,mediaId_3900),
  CONSTRAINT fk_ordermedia_order
    FOREIGN KEY(orderID_3900)
    REFERENCES Order_DungNV(id_3900),
  CONSTRAINT fk_OrderMedia_Media1
    FOREIGN KEY(mediaId_3900)
    REFERENCES Media_DungNV(id_3900)
);
CREATE INDEX aims_OrderMedia_fk_ordermedia_order_idx ON OrderMedia_DungNV (orderID_3900);
CREATE INDEX aims_OrderMedia_fk_OrderMedia_Media1_idx ON OrderMedia_DungNV (mediaId_3900);
CREATE TABLE Invoice_DungNV(
  id_3900 INTEGER PRIMARY KEY NOT NULL,
  totalAmount_3900 INTEGER NOT NULL,
  orderId_3900 INTEGER NOT NULL,
  CONSTRAINT fk_Invoice_Order1
    FOREIGN KEY(orderId_3900)
    REFERENCES Order_DungNV(id_3900)
);
CREATE INDEX aims_Invoice_fk_Invoice_Order1_idx ON Invoice_DungNV (orderId_3900);
CREATE TABLE PaymentTransaction_DungNV(
  id_3900 INTEGER NOT NULL,
  createAt_3900 DATETIME NOT NULL,
  content_3900 VARCHAR(45) NOT NULL,
  method_3900 VARCHAR(45),
  cardId_3900 INTEGER NOT NULL,
  invoiceId_3900 INTEGER NOT NULL,
  PRIMARY KEY(id_3900,cardId_3900,invoiceId_3900),
  CONSTRAINT fk_PaymentTransaction_Card1
    FOREIGN KEY(cardId_3900)
    REFERENCES Card_DungNV(id_3900),
  CONSTRAINT fk_PaymentTransaction_Invoice1
    FOREIGN KEY(invoiceId_3900)
    REFERENCES Invoice_DungNV(id_3900)
);
CREATE INDEX aims_PaymentTransaction_fk_PaymentTransaction_Card1_idx ON PaymentTransaction_DungNV (cardId_3900);
CREATE INDEX aims_PaymentTransaction_fk_PaymentTransaction_Invoice1_idx ON PaymentTransaction_DungNV (invoiceId_3900);
COMMIT;