

CREATE TABLE `client` (
  `id_client` int(11) NOT NULL,
  `enrollment` varchar(18) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `status` enum('ACTIVE','DEACTIVE','SUSPEND') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `email` (
  `id_email` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `category` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE `client`
  ADD PRIMARY KEY (`id_cliente`);


ALTER TABLE `email`
  ADD PRIMARY KEY (`id_email`),
  ADD KEY `id_cliente` (`id_cliente`);


ALTER TABLE `client`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=0;


ALTER TABLE `email`
  MODIFY `id_email` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `email`
  ADD CONSTRAINT `email_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`),
  ADD CONSTRAINT `email_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);
COMMIT;
