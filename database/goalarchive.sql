-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Gen 28, 2026 alle 01:31
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `goalarchive`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `amministratore`
--

CREATE TABLE `amministratore` (
  `email` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `nomeUtente` varchar(30) NOT NULL,
  `dataNascita` date NOT NULL,
  `ruolo` varchar(10) NOT NULL DEFAULT 'admin',
  `password` varchar(255) NOT NULL,
  `domandaSicurezza` varchar(200) NOT NULL,
  `rispostaSicurezza` varchar(100) NOT NULL,
  `livelloAccesso` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `amministratore`
--

INSERT INTO `amministratore` (`email`, `nome`, `cognome`, `nomeUtente`, `dataNascita`, `ruolo`, `password`, `domandaSicurezza`, `rispostaSicurezza`, `livelloAccesso`) VALUES
('admin@goalarchive.com', 'Admin', 'System', 'admin', '1990-01-01', 'admin', 'admin123', 'Qual è il tuo codice admin?', 'admin2025', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `calciatore`
--

CREATE TABLE `calciatore` (
  `idCalciatore` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `ruolo` varchar(30) NOT NULL,
  `nazionalita` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `calciatore`
--

INSERT INTO `calciatore` (`idCalciatore`, `nome`, `cognome`, `ruolo`, `nazionalita`) VALUES
(32, 'Aaron', 'Ramsdale', 'Portiere', 'Inghilterra'),
(79, 'Acerbi', 'Francesco', 'Difensore', 'Italia'),
(137, 'Achraf', 'Hakimi', 'Difensore', 'Marocco'),
(23, 'Ademola', 'Lookman', 'Attaccante', 'Nigeria'),
(134, 'Adrien', 'Rabiot', 'Centrocampista', 'Francia'),
(89, 'Alaba', 'David', 'Difensore', 'Austria'),
(25, 'Alessandro', 'Bastoni', 'Difensore', 'Italia'),
(196, 'Alessandro', 'Buongiorno', 'Difensore', 'Italia'),
(4, 'Alessandro', 'Costacurta', 'Difensore', 'Italia'),
(154, 'Alessandro', 'Nesta', 'Difensore', 'Italia'),
(53, 'Alessio', 'Romagnoli', 'Difensore', 'Italia'),
(40, 'Alex', 'Meret', 'Portiere', 'Italia'),
(66, 'Alexis', 'Mac Allister', 'Centrocampista', 'Argentina'),
(116, 'Alexis', 'Saelemaekers', 'Centrocampista', 'Belgio'),
(64, 'Alisson', 'Becker', 'Portiere', 'Brasile'),
(236, 'Allan', 'Marques Loureiro', 'Centrocampista', 'Brasile'),
(105, 'Amir', 'Rrahmani', 'Difensore', 'Kosovo'),
(107, 'André-Frank', 'Zambo Anguissa', 'Centrocampista', 'Camerun'),
(133, 'Andrea', 'Cambiaso', 'Difensore', 'Italia'),
(157, 'Andrea', 'Pirlo', 'Centrocampista', 'Italia'),
(17, 'Andrés', 'Iniesta', 'Centrocampista', 'Spagna'),
(146, 'Andy', 'Robertson', 'Difensore', 'Scozia'),
(61, 'Antonio', 'Rüdiger', 'Difensore', 'Germania'),
(211, 'Antonio', 'Vergara', 'Centrocampista', 'Italia'),
(97, 'Araújo', 'Ronald', 'Difensore', 'Uruguay'),
(21, 'Berat', 'Djimsiti', 'Difensore', 'Albania'),
(209, 'Billy', 'Gilmour', 'Centrocampista', 'Scozia'),
(132, 'Bremer', 'da Silva', 'Difensore', 'Brasile'),
(35, 'Bukayo', 'Saka', 'Attaccante', 'Inghilterra'),
(155, 'Cafu', 'Marcos Evangelista', 'Difensore', 'Brasile'),
(73, 'Calabria', 'Davide', 'Difensore', 'Italia'),
(83, 'Çalhanoğlu', 'Hakan', 'Centrocampista', 'Turchia'),
(90, 'Camavinga', 'Eduardo', 'Centrocampista', 'Francia'),
(143, 'Caoimhín', 'Kelleher', 'Portiere', 'Irlanda'),
(14, 'Carles', 'Puyol', 'Difensore', 'Spagna'),
(7, 'Carlo', 'Ancelotti', 'Centrocampista', 'Italia'),
(87, 'Carvajal', 'Dani', 'Difensore', 'Spagna'),
(98, 'Christensen', 'Andreas', 'Difensore', 'Danimarca'),
(231, 'Christian', 'Maggio', 'Difensore', 'Italia'),
(77, 'Chukwueze', 'Samuel', 'Attaccante', 'Nigeria'),
(55, 'Ciro', 'Immobile', 'Attaccante', 'Italia'),
(158, 'Clarence', 'Seedorf', 'Centrocampista', 'Olanda'),
(150, 'Cody', 'Gakpo', 'Attaccante', 'Olanda'),
(71, 'Cole', 'Palmer', 'Attaccante', 'Inghilterra'),
(117, 'Dan', 'Ndoye', 'Attaccante', 'Svizzera'),
(80, 'Darmian', 'Matteo', 'Difensore', 'Italia'),
(151, 'Darwin', 'Núñez', 'Attaccante', 'Uruguay'),
(217, 'David', 'Neres', 'Attaccante', 'Brasile'),
(57, 'Dayot', 'Upamecano', 'Difensore', 'Francia'),
(101, 'De Jong', 'Frenkie', 'Centrocampista', 'Olanda'),
(8, 'Demetrio', 'Albertini', 'Centrocampista', 'Italia'),
(122, 'Denzel', 'Dumfries', 'Difensore', 'Olanda'),
(141, 'Désiré', 'Doué', 'Attaccante', 'Francia'),
(152, 'Dida', 'Nelson de Jesus Silva', 'Portiere', 'Brasile'),
(168, 'Diego', 'Milito', 'Attaccante', 'Argentina'),
(81, 'Dimarco', 'Federico', 'Difensore', 'Italia'),
(148, 'Dominik', 'Szoboszlai', 'Centrocampista', 'Ungheria'),
(235, 'Dries', 'Mertens', 'Attaccante', 'Belgio'),
(39, 'Dušan', 'Vlahović', 'Attaccante', 'Serbia'),
(128, 'Éderson', 'José dos Santos', 'Centrocampista', 'Brasile'),
(28, 'Ederson', 'Moraes', 'Portiere', 'Brasile'),
(228, 'Edison', 'Cavani', 'Attaccante', 'Uruguay'),
(104, 'Elia', 'Caprile', 'Portiere', 'Italia'),
(210, 'Eljif', 'Elmas', 'Centrocampista', 'Nord Macedonia'),
(214, 'Emmanuele', 'De Chiara', 'Centrocampista', 'Italia'),
(70, 'Enzo', 'Fernández', 'Centrocampista', 'Argentina'),
(31, 'Erling', 'Haaland', 'Attaccante', 'Norvegia'),
(165, 'Esteban', 'Cambiasso', 'Centrocampista', 'Argentina'),
(229, 'Ezequiel', 'Lavezzi', 'Attaccante', 'Argentina'),
(37, 'Federico', 'Gatti', 'Difensore', 'Italia'),
(6, 'Filippo', 'Galli', 'Difensore', 'Italia'),
(160, 'Filippo', 'Inzaghi', 'Attaccante', 'Italia'),
(213, 'Francisco', 'Baridó', 'Centrocampista', 'Argentina'),
(2, 'Franco', 'Baresi', 'Difensore', 'Italia'),
(75, 'Gabbia', 'Matteo', 'Difensore', 'Italia'),
(99, 'Gavi', 'Pablo', 'Centrocampista', 'Spagna'),
(156, 'Gennaro', 'Gattuso', 'Centrocampista', 'Italia'),
(15, 'Gerard', 'Piqué', 'Difensore', 'Spagna'),
(110, 'Giacomo', 'Raspadori', 'Attaccante', 'Italia'),
(49, 'Gianluca', 'Mancini', 'Difensore', 'Italia'),
(136, 'Gianluigi', 'Donnarumma', 'Portiere', 'Italia'),
(41, 'Giovanni', 'Di Lorenzo', 'Difensore', 'Italia'),
(221, 'Giuseppe', 'Ambrosino', 'Attaccante', 'Italia'),
(232, 'Gökhan', 'Inler', 'Centrocampista', 'Svizzera'),
(59, 'Harry', 'Kane', 'Attaccante', 'Inghilterra'),
(145, 'Ibrahima', 'Konaté', 'Difensore', 'Francia'),
(52, 'Ivan', 'Provedel', 'Portiere', 'Italia'),
(162, 'Javier', 'Zanetti', 'Difensore', 'Argentina'),
(12, 'Jean-Pierre', 'Papin', 'Attaccante', 'Francia'),
(237, 'Jorginho', 'Frello', 'Centrocampista', 'Italia'),
(58, 'Joshua', 'Kimmich', 'Centrocampista', 'Germania'),
(118, 'Joshua', 'Zirkzee', 'Attaccante', 'Olanda'),
(197, 'Juan', 'Jesus', 'Difensore', 'Brasile'),
(20, 'Juan', 'Musso', 'Portiere', 'Argentina'),
(62, 'Jude', 'Bellingham', 'Centrocampista', 'Inghilterra'),
(161, 'Júlio', 'César', 'Portiere', 'Brasile'),
(159, 'Kaká', 'Ricardo Izecson', 'Attaccante', 'Brasile'),
(234, 'Kalidou', 'Koulibaly', 'Difensore', 'Senegal'),
(135, 'Kenan', 'Yıldız', 'Attaccante', 'Turchia'),
(30, 'Kevin', 'De Bruyne', 'Centrocampista', 'Belgio'),
(111, 'Khvicha', 'Kvaratskhelia', 'Attaccante', 'Georgia'),
(96, 'Koundé', 'Jules', 'Difensore', 'Francia'),
(27, 'Lautaro', 'Martínez', 'Attaccante', 'Argentina'),
(222, 'Leonardo', 'Spinazzola', 'Difensore', 'Italia'),
(69, 'Levi', 'Colwill', 'Difensore', 'Inghilterra'),
(103, 'Lewandowski', 'Robert', 'Attaccante', 'Polonia'),
(18, 'Lionel', 'Messi', 'Attaccante', 'Argentina'),
(76, 'Loftus-Cheek', 'Ruben', 'Centrocampista', 'Inghilterra'),
(238, 'Lorenzo', 'Insigne', 'Attaccante', 'Italia'),
(220, 'Lorenzo', 'Lucca', 'Attaccante', 'Italia'),
(50, 'Lorenzo', 'Pellegrini', 'Centrocampista', 'Italia'),
(199, 'Luca', 'Marianucci', 'Difensore', 'Italia'),
(163, 'Lúcio', 'Ferreira da Silva', 'Difensore', 'Brasile'),
(149, 'Luis', 'Díaz', 'Attaccante', 'Colombia'),
(86, 'Lunin', 'Andriy', 'Portiere', 'Ucraina'),
(164, 'Maicon', 'Douglas Sisenando', 'Difensore', 'Brasile'),
(38, 'Manuel', 'Locatelli', 'Centrocampista', 'Italia'),
(56, 'Manuel', 'Neuer', 'Portiere', 'Germania'),
(11, 'Marco', 'Van Basten', 'Attaccante', 'Olanda'),
(230, 'Marek', 'Hamsik', 'Centrocampista', 'Slovacchia'),
(138, 'Marquinhos', 'Corrêa', 'Difensore', 'Brasile'),
(22, 'Marten', 'de Roon', 'Centrocampista', 'Olanda'),
(34, 'Martin', 'Ødegaard', 'Centrocampista', 'Norvegia'),
(130, 'Mateo', 'Retegui', 'Attaccante', 'Italia'),
(195, 'Mathias', 'Ferrante', 'Portiere', 'Italia'),
(106, 'Mathías', 'Olivera', 'Difensore', 'Uruguay'),
(54, 'Mattéo', 'Guendouzi', 'Centrocampista', 'Francia'),
(109, 'Matteo', 'Politano', 'Attaccante', 'Italia'),
(131, 'Mattia', 'Perin', 'Portiere', 'Italia'),
(5, 'Mauro', 'Tassotti', 'Difensore', 'Italia'),
(94, 'Mbappé', 'Kylian', 'Attaccante', 'Francia'),
(198, 'Miguel', 'Gutiérrez', 'Difensore', 'Spagna'),
(44, 'Mike', 'Maignan', 'Portiere', 'Francia'),
(48, 'Mile', 'Svilar', 'Portiere', 'Serbia'),
(88, 'Militão', 'Éder', 'Difensore', 'Brasile'),
(84, 'Mkhitaryan', 'Henrikh', 'Centrocampista', 'Armenia'),
(91, 'Modrić', 'Luka', 'Centrocampista', 'Croazia'),
(67, 'Mohamed', 'Salah', 'Attaccante', 'Egitto'),
(26, 'Nicolò', 'Barella', 'Centrocampista', 'Italia'),
(193, 'Nikita', 'Contini', 'Portiere', 'Italia'),
(219, 'Noa', 'Lang', 'Attaccante', 'Olanda'),
(3, 'Paolo', 'Maldini', 'Difensore', 'Italia'),
(223, 'Pasquale', 'Mazzocchi', 'Difensore', 'Italia'),
(51, 'Paulo', 'Dybala', 'Attaccante', 'Argentina'),
(82, 'Pavard', 'Benjamin', 'Difensore', 'Francia'),
(100, 'Pedri', 'González', 'Centrocampista', 'Spagna'),
(233, 'Pepe', 'Reina', 'Portiere', 'Spagna'),
(108, 'Piotr', 'Zieliński', 'Centrocampista', 'Polonia'),
(78, 'Pulisic', 'Christian', 'Attaccante', 'USA'),
(47, 'Rafael', 'Leão', 'Attaccante', 'Portogallo'),
(102, 'Raphinha', 'Dias', 'Attaccante', 'Brasile'),
(218, 'Rasmus', 'Højlund', 'Attaccante', 'Danimarca'),
(115, 'Remo', 'Freuler', 'Centrocampista', 'Svizzera'),
(114, 'Riccardo', 'Calafiori', 'Difensore', 'Italia'),
(119, 'Riccardo', 'Orsolini', 'Attaccante', 'Italia'),
(68, 'Robert', 'Sánchez', 'Portiere', 'Spagna'),
(9, 'Roberto', 'Donadoni', 'Centrocampista', 'Italia'),
(93, 'Rodrygo', 'Goes', 'Attaccante', 'Brasile'),
(216, 'Romelu', 'Lukaku', 'Attaccante', 'Belgio'),
(29, 'Rúben', 'Dias', 'Difensore', 'Portogallo'),
(10, 'Ruud', 'Gullit', 'Centrocampista', 'Olanda'),
(147, 'Ryan', 'Gravenberch', 'Centrocampista', 'Olanda'),
(113, 'Sam', 'Beukema', 'Difensore', 'Olanda'),
(19, 'Samuel', 'Eto\'o', 'Attaccante', 'Camerun'),
(208, 'Scott', 'McTominay', 'Centrocampista', 'Scozia'),
(1, 'Sebastiano', 'Rossi', 'Portiere', 'Italia'),
(142, 'Senny', 'Mayulu', 'Attaccante', 'Francia'),
(72, 'Sportiello', 'Marco', 'Portiere', 'Italia'),
(42, 'Stanislav', 'Lobotka', 'Centrocampista', 'Slovacchia'),
(95, 'Ter Stegen', 'Marc-André', 'Portiere', 'Germania'),
(45, 'Theo', 'Hernández', 'Difensore', 'Francia'),
(60, 'Thibaut', 'Courtois', 'Portiere', 'Belgio'),
(85, 'Thuram', 'Marcus', 'Attaccante', 'Francia'),
(46, 'Tijjani', 'Reijnders', 'Centrocampista', 'Olanda'),
(74, 'Tomori', 'Fikayo', 'Difensore', 'Inghilterra'),
(144, 'Trent', 'Alexander-Arnold', 'Difensore', 'Inghilterra'),
(92, 'Valverde', 'Federico', 'Centrocampista', 'Uruguay'),
(194, 'Vanja', 'Milinković-Savić', 'Portiere', 'Serbia'),
(43, 'Victor', 'Osimhen', 'Attaccante', 'Nigeria'),
(13, 'Victor', 'Valdés', 'Portiere', 'Spagna'),
(212, 'Vincenzo', 'Prisco', 'Centrocampista', 'Italia'),
(63, 'Vinícius', 'Júnior', 'Attaccante', 'Brasile'),
(65, 'Virgil', 'van Dijk', 'Difensore', 'Olanda'),
(139, 'Vitinha', 'Ferreira', 'Centrocampista', 'Portogallo'),
(140, 'Warren', 'Zaïre-Emery', 'Centrocampista', 'Francia'),
(166, 'Wesley', 'Sneijder', 'Centrocampista', 'Olanda'),
(33, 'William', 'Saliba', 'Difensore', 'Francia'),
(36, 'Wojciech', 'Szczęsny', 'Portiere', 'Polonia'),
(16, 'Xavi', 'Hernández', 'Centrocampista', 'Spagna'),
(24, 'Yann', 'Sommer', 'Portiere', 'Svizzera'),
(112, 'Łukasz', 'Skorupski', 'Portiere', 'Polonia');

-- --------------------------------------------------------

--
-- Struttura della tabella `club`
--

CREATE TABLE `club` (
  `idClub` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `nazione` varchar(30) NOT NULL,
  `campionato` varchar(30) NOT NULL,
  `annoFondazione` int(11) DEFAULT NULL,
  `stadio` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `club`
--

INSERT INTO `club` (`idClub`, `nome`, `nazione`, `campionato`, `annoFondazione`, `stadio`) VALUES
(1, 'AC Milan', 'Italia', 'Serie A', 1899, 'San Siro'),
(2, 'FC Internazionale', 'Italia', 'Serie A', 1908, 'San Siro'),
(3, 'Juventus FC', 'Italia', 'Serie A', 1897, 'Allianz Stadium'),
(4, 'SSC Napoli', 'Italia', 'Serie A', 1926, 'Diego Armando Maradona'),
(5, 'AS Roma', 'Italia', 'Serie A', 1927, 'Olimpico'),
(6, 'FC Barcelona', 'Spagna', 'La Liga', 1899, 'Camp Nou'),
(7, 'Real Madrid CF', 'Spagna', 'La Liga', 1902, 'Santiago Bernabéu'),
(8, 'Manchester United', 'Inghilterra', 'Premier League', 1878, 'Old Trafford'),
(9, 'Liverpool FC', 'Inghilterra', 'Premier League', 1892, 'Anfield'),
(10, 'Bayern Monaco', 'Germania', 'Bundesliga', NULL, NULL),
(11, 'Atalanta BC', 'Italia', 'Serie A', 1907, 'Gewiss Stadium'),
(12, 'ACF Fiorentina', 'Italia', 'Serie A', 1926, 'Artemio Franchi'),
(13, 'SS Lazio', 'Italia', 'Serie A', 1900, 'Olimpico'),
(14, 'Bologna FC', 'Italia', 'Serie A', 1909, 'Renato Dall\'Ara'),
(15, 'Torino FC', 'Italia', 'Serie A', 1906, 'Olimpico Grande Torino'),
(16, 'Udinese Calcio', 'Italia', 'Serie A', 1896, 'Bluenergy Stadium'),
(18, 'Hellas Verona', 'Italia', 'Serie A', 1903, 'Marcantonio Bentegodi'),
(19, 'Cagliari Calcio', 'Italia', 'Serie A', 1920, 'Unipol Domus'),
(20, 'Lecce', 'Italia', 'Serie A', 1908, 'Via del Mare'),
(21, 'Parma Calcio', 'Italia', 'Serie A', 1913, 'Ennio Tardini'),
(22, 'Como 1907', 'Italia', 'Serie A', 1907, 'Giuseppe Sinigaglia'),
(24, 'Genoa CFC', 'Italia', 'Serie A', 1893, 'Luigi Ferraris'),
(26, 'Atletico Madrid', 'Spagna', 'La Liga', 1903, 'Civitas Metropolitano'),
(27, 'Athletic Bilbao', 'Spagna', 'La Liga', 1898, 'San Mamés'),
(28, 'Villarreal CF', 'Spagna', 'La Liga', 1923, 'Estadio de la Cerámica'),
(29, 'Bayer Leverkusen', 'Germania', 'Bundesliga', 1904, 'BayArena'),
(30, 'Borussia Dortmund', 'Germania', 'Bundesliga', 1909, 'Signal Iduna Park'),
(31, 'RB Leipzig', 'Germania', 'Bundesliga', 2009, 'Red Bull Arena'),
(32, 'Manchester City', 'Inghilterra', 'Premier League', 1894, 'Etihad Stadium'),
(33, 'Arsenal FC', 'Inghilterra', 'Premier League', 1886, 'Emirates Stadium'),
(34, 'Chelsea FC', 'Inghilterra', 'Premier League', 1905, 'Stamford Bridge'),
(35, 'Tottenham Hotspur', 'Inghilterra', 'Premier League', 1882, 'Tottenham Hotspur Stadium');

-- --------------------------------------------------------

--
-- Struttura della tabella `commento`
--

CREATE TABLE `commento` (
  `idCommento` int(11) NOT NULL,
  `idTopic` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `testo` varchar(1000) NOT NULL,
  `dataPubblicazione` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `commento`
--

INSERT INTO `commento` (`idCommento`, `idTopic`, `email`, `testo`, `dataPubblicazione`) VALUES
(11, 10, 'domelap@gmail.com', 'el matador cavani', '2026-01-27 23:23:35'),
(12, 9, 'domelap@gmail.com', 'eauhfdaueikfhesoiufhewsiukfh', '2026-01-28 01:11:18');

-- --------------------------------------------------------

--
-- Struttura della tabella `competizione`
--

CREATE TABLE `competizione` (
  `idCompetizione` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `nazione` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `competizione`
--

INSERT INTO `competizione` (`idCompetizione`, `nome`, `tipo`, `nazione`) VALUES
(1, 'Serie A', 'Nazionale', 'Italia'),
(2, 'Coppa Italia', 'Nazionale', 'Italia'),
(3, 'UEFA Champions League', 'Internazionale', NULL),
(4, 'UEFA Europa League', 'Internazionale', NULL),
(5, 'La Liga', 'Nazionale', 'Spagna'),
(6, 'Copa del Rey', 'Nazionale', 'Spagna'),
(7, 'Premier League', 'Nazionale', 'Inghilterra'),
(8, 'FA Cup', 'Nazionale', 'Inghilterra'),
(9, 'Bundesliga', 'Nazionale', 'Germania');

-- --------------------------------------------------------

--
-- Struttura della tabella `preferito`
--

CREATE TABLE `preferito` (
  `idPreferito` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `idRiferimento` int(11) NOT NULL,
  `dataAggiunta` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `preferito`
--

INSERT INTO `preferito` (`idPreferito`, `email`, `tipo`, `idRiferimento`, `dataAggiunta`) VALUES
(22, 'domelap@gmail.com', 'club', 22, '2026-01-28 01:11:29'),
(23, 'domelap@gmail.com', 'calciatore', 95, '2026-01-28 01:11:39');

-- --------------------------------------------------------

--
-- Struttura della tabella `rosastagionale`
--

CREATE TABLE `rosastagionale` (
  `idRosaStagionale` int(11) NOT NULL,
  `idClub` int(11) NOT NULL,
  `idCalciatore` int(11) NOT NULL,
  `stagione` varchar(10) NOT NULL,
  `presenze` int(11) DEFAULT 0,
  `gol` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `rosastagionale`
--

INSERT INTO `rosastagionale` (`idRosaStagionale`, `idClub`, `idCalciatore`, `stagione`, `presenze`, `gol`) VALUES
(1, 1, 1, '1991-92', 34, 0),
(2, 1, 2, '1991-92', 31, 2),
(3, 1, 3, '1991-92', 33, 1),
(4, 1, 4, '1991-92', 30, 0),
(5, 1, 5, '1991-92', 32, 1),
(6, 1, 6, '1991-92', 25, 0),
(7, 1, 7, '1991-92', 28, 3),
(8, 1, 8, '1991-92', 27, 2),
(9, 1, 9, '1991-92', 31, 6),
(10, 1, 10, '1991-92', 29, 4),
(11, 1, 11, '1991-92', 31, 25),
(12, 1, 12, '1991-92', 29, 13),
(13, 6, 13, '2008-09', 37, 0),
(14, 6, 14, '2008-09', 35, 2),
(15, 6, 15, '2008-09', 34, 1),
(16, 6, 16, '2008-09', 38, 4),
(17, 6, 17, '2008-09', 37, 5),
(18, 6, 18, '2008-09', 38, 38),
(19, 6, 19, '2008-09', 36, 36),
(24, 2, 24, '2024-25', 22, 0),
(25, 2, 25, '2024-25', 21, 2),
(26, 2, 26, '2024-25', 23, 3),
(27, 2, 27, '2024-25', 22, 12),
(28, 32, 28, '2024-25', 25, 0),
(29, 32, 29, '2024-25', 24, 1),
(30, 32, 30, '2024-25', 23, 5),
(31, 32, 31, '2024-25', 26, 22),
(32, 33, 32, '2024-25', 24, 0),
(33, 33, 33, '2024-25', 25, 2),
(34, 33, 34, '2024-25', 26, 6),
(35, 33, 35, '2024-25', 25, 10),
(36, 3, 36, '2024-25', 20, 0),
(37, 3, 37, '2024-25', 22, 2),
(38, 3, 38, '2024-25', 21, 1),
(39, 3, 39, '2024-25', 23, 10),
(40, 4, 40, '2024-25', 20, 0),
(44, 1, 44, '2024-25', 23, 0),
(45, 1, 45, '2024-25', 22, 2),
(46, 1, 46, '2024-25', 21, 3),
(47, 1, 47, '2024-25', 24, 8),
(48, 5, 48, '2024-25', 20, 0),
(49, 5, 49, '2024-25', 22, 1),
(50, 5, 50, '2024-25', 19, 2),
(51, 5, 51, '2024-25', 21, 7),
(52, 13, 52, '2024-25', 21, 0),
(53, 13, 53, '2024-25', 23, 1),
(54, 13, 54, '2024-25', 22, 2),
(55, 13, 55, '2024-25', 20, 9),
(56, 10, 56, '2024-25', 22, 0),
(57, 10, 57, '2024-25', 24, 2),
(58, 10, 58, '2024-25', 25, 4),
(59, 10, 59, '2024-25', 23, 18),
(60, 7, 60, '2024-25', 20, 0),
(61, 7, 61, '2024-25', 24, 1),
(62, 7, 62, '2024-25', 25, 12),
(63, 7, 63, '2024-25', 26, 15),
(68, 34, 68, '2024-25', 21, 0),
(69, 34, 69, '2024-25', 20, 0),
(70, 34, 70, '2024-25', 23, 3),
(71, 34, 71, '2024-25', 24, 11),
(72, 1, 72, '2024-25', 5, 0),
(73, 1, 73, '2024-25', 18, 0),
(74, 1, 74, '2024-25', 21, 1),
(75, 1, 75, '2024-25', 19, 2),
(76, 1, 76, '2024-25', 20, 3),
(77, 1, 77, '2024-25', 17, 2),
(78, 1, 78, '2024-25', 22, 7),
(79, 2, 79, '2024-25', 15, 3),
(80, 2, 80, '2024-25', 18, 0),
(81, 2, 81, '2024-25', 23, 4),
(82, 2, 82, '2024-25', 22, 1),
(83, 2, 83, '2024-25', 21, 5),
(84, 2, 84, '2024-25', 20, 3),
(85, 2, 85, '2024-25', 24, 11),
(86, 7, 86, '2024-25', 15, 0),
(87, 7, 87, '2024-25', 18, 1),
(88, 7, 88, '2024-25', 16, 2),
(89, 7, 89, '2024-25', 10, 0),
(90, 7, 90, '2024-25', 22, 3),
(91, 7, 91, '2024-25', 19, 2),
(92, 7, 92, '2024-25', 24, 8),
(93, 7, 93, '2024-25', 23, 10),
(94, 7, 94, '2024-25', 20, 15),
(95, 6, 95, '2024-25', 15, 0),
(96, 6, 96, '2024-25', 21, 2),
(97, 6, 97, '2024-25', 18, 1),
(98, 6, 98, '2024-25', 16, 0),
(99, 6, 99, '2024-25', 12, 2),
(100, 6, 100, '2024-25', 20, 5),
(101, 6, 101, '2024-25', 19, 1),
(102, 6, 102, '2024-25', 22, 8),
(103, 6, 103, '2024-25', 24, 20),
(104, 4, 104, '2024-25', 20, 0),
(105, 4, 105, '2024-25', 36, 3),
(106, 4, 106, '2024-25', 34, 2),
(107, 4, 41, '2024-25', 38, 3),
(108, 4, 107, '2024-25', 37, 5),
(109, 4, 42, '2024-25', 36, 2),
(110, 4, 108, '2024-25', 30, 6),
(111, 4, 109, '2024-25', 35, 9),
(112, 4, 110, '2024-25', 28, 7),
(113, 4, 111, '2024-25', 37, 16),
(114, 4, 43, '2024-25', 35, 18),
(115, 14, 112, '2024-25', 38, 0),
(116, 14, 113, '2024-25', 35, 2),
(117, 14, 114, '2024-25', 32, 1),
(118, 14, 115, '2024-25', 36, 3),
(119, 14, 116, '2024-25', 30, 4),
(120, 14, 117, '2024-25', 34, 8),
(121, 14, 118, '2024-25', 33, 15),
(122, 14, 119, '2024-25', 36, 11),
(123, 2, 24, '2025-26', 20, 0),
(124, 2, 25, '2025-26', 19, 1),
(125, 2, 81, '2025-26', 18, 3),
(126, 2, 122, '2025-26', 17, 0),
(127, 2, 26, '2025-26', 20, 4),
(128, 2, 83, '2025-26', 19, 3),
(129, 2, 27, '2025-26', 21, 11),
(130, 2, 85, '2025-26', 20, 9),
(131, 11, 20, '2024-25', 35, 0),
(132, 11, 21, '2024-25', 36, 2),
(134, 11, 128, '2024-25', 35, 7),
(135, 11, 23, '2024-25', 37, 14),
(136, 11, 130, '2024-25', 38, 25),
(137, 3, 131, '2025-26', 12, 0),
(138, 3, 36, '2025-26', 15, 0),
(139, 3, 132, '2025-26', 18, 2),
(140, 3, 133, '2025-26', 19, 1),
(141, 3, 134, '2025-26', 17, 2),
(142, 3, 38, '2025-26', 18, 1),
(143, 3, 135, '2025-26', 16, 5),
(144, 3, 39, '2025-26', 19, 8),
(145, 35, 136, '2024-25', 45, 0),
(146, 35, 137, '2024-25', 48, 5),
(147, 35, 138, '2024-25', 42, 3),
(148, 35, 139, '2024-25', 46, 8),
(149, 35, 140, '2024-25', 40, 6),
(150, 35, 111, '2024-25', 44, 18),
(151, 35, 141, '2024-25', 35, 12),
(152, 35, 142, '2024-25', 30, 7),
(153, 9, 143, '2024-25', 15, 0),
(154, 9, 64, '2024-25', 35, 0),
(155, 9, 144, '2024-25', 36, 4),
(156, 9, 65, '2024-25', 34, 2),
(157, 9, 145, '2024-25', 30, 1),
(158, 9, 146, '2024-25', 35, 3),
(159, 9, 147, '2024-25', 38, 5),
(160, 9, 148, '2024-25', 35, 8),
(161, 9, 66, '2024-25', 33, 6),
(162, 9, 149, '2024-25', 37, 16),
(163, 9, 150, '2024-25', 32, 12),
(164, 9, 151, '2024-25', 36, 14),
(165, 9, 67, '2024-25', 38, 22),
(166, 1, 152, '2006-07', 35, 0),
(167, 1, 3, '2006-07', 32, 1),
(168, 1, 154, '2006-07', 28, 2),
(169, 1, 155, '2006-07', 30, 1),
(170, 1, 156, '2006-07', 35, 2),
(171, 1, 157, '2006-07', 34, 7),
(172, 1, 158, '2006-07', 36, 5),
(173, 1, 159, '2006-07', 36, 14),
(174, 1, 160, '2006-07', 34, 16),
(175, 2, 161, '2009-10', 40, 0),
(176, 2, 162, '2009-10', 38, 2),
(177, 2, 163, '2009-10', 35, 4),
(178, 2, 164, '2009-10', 36, 5),
(179, 2, 165, '2009-10', 37, 3),
(180, 2, 166, '2009-10', 35, 6),
(181, 2, 19, '2009-10', 35, 16),
(182, 2, 168, '2009-10', 38, 30),
(183, 4, 208, '2025-26', 20, 5),
(184, 4, 30, '2025-26', 17, 6),
(185, 4, 42, '2025-26', 18, 1),
(186, 4, 107, '2025-26', 19, 3),
(187, 4, 209, '2025-26', 12, 1),
(188, 4, 210, '2025-26', 15, 3),
(189, 4, 216, '2025-26', 15, 8),
(190, 4, 218, '2025-26', 16, 7),
(191, 4, 219, '2025-26', 17, 6),
(192, 4, 109, '2025-26', 20, 5),
(193, 4, 217, '2025-26', 18, 4),
(194, 4, 41, '2025-26', 20, 2),
(195, 4, 196, '2025-26', 19, 2),
(196, 4, 105, '2025-26', 18, 1),
(197, 4, 40, '2025-26', 12, 0),
(198, 4, 194, '2025-26', 10, 0),
(199, 4, 228, '2011-12', 35, 23),
(200, 4, 229, '2011-12', 30, 9),
(201, 4, 230, '2011-12', 37, 9),
(202, 4, 231, '2011-12', 33, 3),
(203, 4, 232, '2011-12', 36, 0),
(227, 4, 233, '2017-18', 37, 0),
(228, 4, 234, '2017-18', 35, 5),
(229, 4, 235, '2017-18', 38, 18),
(230, 4, 238, '2017-18', 37, 8);

-- --------------------------------------------------------

--
-- Struttura della tabella `topic`
--

CREATE TABLE `topic` (
  `idTopic` int(11) NOT NULL,
  `titolo` varchar(100) NOT NULL,
  `descrizione` text NOT NULL,
  `dataCreazione` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `topic`
--

INSERT INTO `topic` (`idTopic`, `titolo`, `descrizione`, `dataCreazione`) VALUES
(1, 'Qual è stata la partita più iconica degli anni 90?', 'Discutiamo delle partite storiche che hanno segnato il calcio negli anni novanta.', '2025-11-26 00:22:20'),
(2, 'Il miglior attaccante di sempre', 'Chi è secondo voi il miglior centravanti della storia del calcio?', '2025-11-26 00:22:20'),
(3, 'Le rose più forti della storia', 'Quali sono state le squadre con le rose più complete e competitive?', '2025-11-26 00:22:20'),
(4, 'Il calcio moderno vs quello degli anni 80-90', 'Come è cambiato il gioco? È migliorato o peggiorato?', '2025-11-26 00:22:20'),
(5, 'Qual è stata la partita più iconica degli anni 90?', 'Discutiamo delle partite storiche che hanno segnato il calcio negli anni novanta.', '2025-11-26 00:24:16'),
(6, 'Il miglior attaccante di sempre', 'Chi è secondo voi il miglior centravanti della storia del calcio?', '2025-11-26 00:24:16'),
(7, 'Le rose più forti della storia', 'Quali sono state le squadre con le rose più complete e competitive?', '2025-11-26 00:24:16'),
(8, 'Il calcio moderno vs quello degli anni 80-90', 'Come è cambiato il gioco? È migliorato o peggiorato?', '2025-11-26 00:24:16'),
(9, 'Qual è stata la partita più iconica degli anni 90?', 'Discutiamo delle partite storiche che hanno segnato il calcio negli anni novanta.', '2025-11-26 00:24:44'),
(10, 'Il miglior attaccante di sempre', 'Chi è secondo voi il miglior centravanti della storia del calcio?', '2025-11-26 00:24:44'),
(11, 'Le rose più forti della storia', 'Quali sono state le squadre con le rose più complete e competitive?', '2025-11-26 00:24:44'),
(12, 'Il calcio moderno vs quello degli anni 80-90', 'Come è cambiato il gioco? È migliorato o peggiorato?', '2025-11-26 00:24:44');

-- --------------------------------------------------------

--
-- Struttura della tabella `trofeo`
--

CREATE TABLE `trofeo` (
  `idTrofeo` int(11) NOT NULL,
  `idClub` int(11) NOT NULL,
  `anno` int(11) NOT NULL,
  `idCompetizione` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `trofeo`
--

INSERT INTO `trofeo` (`idTrofeo`, `idClub`, `anno`, `idCompetizione`) VALUES
(1, 1, 1992, 1),
(2, 1, 1994, 3),
(3, 1, 2003, 2),
(4, 1, 2007, 3),
(5, 1, 2011, 1),
(7, 6, 2006, 3),
(8, 6, 2009, 3),
(9, 6, 2011, 3),
(10, 6, 2015, 3),
(11, 6, 2008, 5),
(12, 6, 2010, 5),
(13, 6, 2011, 5),
(15, 4, 1987, 1),
(16, 4, 1990, 1),
(17, 4, 2020, 2),
(18, 4, 2023, 1),
(19, 9, 2020, 3),
(20, 9, 2000, 4),
(22, 5, 2000, 8),
(23, 9, 2001, 7),
(24, 9, 2004, 2),
(25, 5, 2002, 2),
(26, 11, 2024, 4),
(27, 13, 2000, 1),
(28, 13, 2019, 2),
(29, 12, 1969, 1),
(30, 12, 2001, 2),
(31, 15, 1976, 1),
(32, 26, 2014, 5),
(33, 26, 2021, 5),
(34, 26, 2012, 4),
(35, 26, 2018, 4),
(36, 27, 1984, 5),
(37, 27, 2024, 6),
(38, 28, 2021, 4),
(39, 29, 2024, 9),
(40, 29, 1993, 2),
(41, 30, 2012, 9),
(42, 30, 1997, 3),
(43, 31, 2022, 2),
(44, 32, 2023, 7),
(45, 32, 2024, 7),
(46, 32, 2023, 3),
(47, 33, 2004, 7),
(48, 33, 2020, 8),
(49, 34, 2021, 3),
(50, 34, 2017, 7),
(51, 35, 2008, 8),
(52, 12, 1956, 1),
(53, 12, 1961, 2),
(54, 12, 1966, 2),
(55, 12, 1975, 2),
(56, 12, 1996, 2),
(57, 13, 1974, 1),
(58, 13, 2000, 2),
(59, 13, 2004, 2),
(60, 13, 2009, 2),
(61, 13, 2013, 2),
(62, 14, 1964, 1),
(63, 14, 1974, 2),
(64, 15, 1943, 1),
(65, 15, 1946, 1),
(66, 15, 1947, 1),
(67, 15, 1948, 1),
(68, 15, 1949, 1),
(69, 24, 1923, 1),
(70, 2, 2006, 1),
(71, 2, 2007, 1),
(72, 2, 2008, 1),
(73, 2, 2009, 1),
(74, 2, 2010, 1),
(75, 2, 2010, 3),
(76, 2, 2021, 1),
(77, 2, 2022, 1),
(78, 2, 2024, 1),
(79, 3, 2012, 1),
(80, 3, 2013, 1),
(81, 3, 2014, 1),
(82, 3, 2015, 1),
(83, 3, 2016, 1),
(84, 3, 2017, 1),
(85, 3, 2018, 1),
(86, 3, 2019, 1),
(87, 3, 2020, 1),
(88, 3, 1996, 3),
(89, 1, 1999, 1),
(90, 1, 2004, 1),
(91, 1, 1989, 3),
(92, 1, 1990, 3),
(93, 1, 2003, 3),
(94, 5, 1983, 1),
(95, 5, 2001, 1),
(96, 5, 2007, 2),
(97, 5, 2008, 2),
(98, 7, 2012, 5),
(99, 7, 2017, 5),
(100, 7, 2022, 5),
(101, 7, 2024, 5),
(102, 7, 2014, 3),
(103, 7, 2016, 3),
(104, 7, 2017, 3),
(105, 7, 2018, 3),
(106, 7, 2022, 3),
(107, 7, 2024, 3),
(108, 10, 2013, 9),
(109, 10, 2014, 9),
(110, 10, 2015, 9),
(111, 10, 2016, 9),
(112, 10, 2017, 9),
(113, 10, 2018, 9),
(114, 10, 2019, 9),
(115, 10, 2020, 9),
(116, 10, 2021, 9),
(117, 10, 2022, 9),
(118, 10, 2023, 9),
(119, 10, 2013, 3),
(120, 10, 2020, 3),
(121, 30, 2011, 9),
(122, 30, 2024, 9),
(123, 8, 1999, 7),
(124, 8, 2008, 7),
(125, 8, 2009, 7),
(126, 8, 2011, 7),
(127, 8, 2013, 7),
(128, 8, 1999, 3),
(129, 8, 2008, 3),
(130, 9, 2020, 7),
(131, 9, 2005, 3),
(132, 9, 2019, 3),
(133, 9, 2022, 8),
(134, 9, 2024, 8),
(135, 32, 2012, 7),
(136, 32, 2014, 7),
(137, 32, 2018, 7),
(138, 32, 2019, 7),
(139, 32, 2021, 7),
(140, 32, 2022, 7),
(141, 33, 2002, 7),
(142, 33, 2014, 8),
(143, 33, 2015, 8),
(144, 33, 2017, 8),
(215, 30, 2002, 9),
(238, 34, 2005, 7),
(239, 34, 2006, 7),
(240, 34, 2010, 7),
(241, 34, 2015, 7),
(242, 34, 2012, 3),
(243, 1, 1901, 1),
(244, 1, 1906, 1),
(245, 1, 1907, 1),
(246, 1, 1951, 1),
(247, 1, 1955, 1),
(248, 1, 1957, 1),
(249, 1, 1959, 1),
(250, 1, 1962, 1),
(251, 1, 1968, 1),
(252, 1, 1979, 1),
(253, 1, 1988, 1),
(254, 1, 1993, 1),
(255, 1, 1994, 1),
(256, 1, 1996, 1),
(257, 1, 1963, 3),
(258, 1, 1969, 3),
(259, 1, 2022, 1),
(260, 1, 1967, 2),
(261, 1, 1972, 2),
(262, 1, 1973, 2),
(263, 1, 1977, 2),
(264, 2, 1910, 1),
(265, 2, 1920, 1),
(266, 2, 1930, 1),
(267, 2, 1938, 1),
(268, 2, 1940, 1),
(269, 2, 1953, 1),
(270, 2, 1954, 1),
(271, 2, 1963, 1),
(272, 2, 1965, 1),
(273, 2, 1966, 1),
(274, 2, 1971, 1),
(275, 2, 1980, 1),
(276, 2, 1989, 1),
(277, 2, 2023, 1),
(278, 2, 1964, 3),
(279, 2, 1965, 3),
(280, 2, 1978, 2),
(281, 2, 1982, 2),
(282, 2, 2005, 2),
(283, 2, 2006, 2),
(284, 2, 2011, 2),
(285, 2, 2023, 2),
(286, 3, 1905, 1),
(287, 3, 1926, 1),
(288, 3, 1931, 1),
(289, 3, 1932, 1),
(290, 3, 1933, 1),
(291, 3, 1934, 1),
(292, 3, 1935, 1),
(293, 3, 1950, 1),
(294, 3, 1952, 1),
(295, 3, 1958, 1),
(296, 3, 1960, 1),
(297, 3, 1961, 1),
(298, 3, 1967, 1),
(299, 3, 1972, 1),
(300, 3, 1973, 1),
(301, 3, 1975, 1),
(302, 3, 1977, 1),
(303, 3, 1978, 1),
(304, 3, 1981, 1),
(305, 3, 1982, 1),
(306, 3, 1984, 1),
(307, 3, 1986, 1),
(308, 3, 1995, 1),
(309, 3, 1997, 1),
(310, 3, 1998, 1),
(311, 3, 2002, 1),
(312, 3, 2003, 1),
(313, 3, 1985, 3),
(314, 3, 1938, 2),
(315, 3, 1942, 2),
(316, 3, 1959, 2),
(317, 3, 1960, 2),
(318, 3, 1965, 2),
(319, 3, 1979, 2),
(320, 3, 1983, 2),
(321, 3, 1990, 2),
(322, 3, 1995, 2),
(323, 3, 2015, 2),
(324, 3, 2016, 2),
(325, 3, 2017, 2),
(326, 3, 2018, 2),
(327, 3, 2021, 2),
(328, 3, 2024, 2),
(329, 4, 2012, 2),
(330, 4, 2014, 2),
(331, 5, 1942, 1),
(332, 5, 1964, 2),
(333, 5, 1969, 2),
(334, 5, 1980, 2),
(335, 5, 1981, 2),
(336, 5, 1984, 2),
(337, 5, 1986, 2),
(338, 5, 1991, 2),
(339, 13, 1958, 2),
(340, 13, 1998, 2),
(341, 12, 1940, 2),
(342, 11, 1963, 2),
(343, 7, 1956, 3),
(344, 7, 1957, 3),
(345, 7, 1958, 3),
(346, 7, 1959, 3),
(347, 7, 1960, 3),
(348, 7, 1966, 3),
(350, 7, 2000, 3),
(351, 7, 2002, 3),
(358, 7, 1932, 5),
(359, 7, 1933, 5),
(360, 7, 1954, 5),
(361, 7, 1955, 5),
(362, 7, 1957, 5),
(363, 7, 1958, 5),
(364, 7, 1961, 5),
(365, 7, 1962, 5),
(366, 7, 1963, 5),
(367, 7, 1964, 5),
(368, 7, 1965, 5),
(369, 7, 1967, 5),
(370, 7, 1968, 5),
(371, 7, 1969, 5),
(372, 7, 1972, 5),
(373, 7, 1975, 5),
(374, 7, 1976, 5),
(375, 7, 1978, 5),
(376, 7, 1979, 5),
(377, 7, 1980, 5),
(378, 7, 1986, 5),
(379, 7, 1987, 5),
(380, 7, 1988, 5),
(381, 7, 1989, 5),
(382, 7, 1990, 5),
(383, 7, 1995, 5),
(384, 7, 1997, 5),
(385, 7, 2001, 5),
(386, 7, 2003, 5),
(387, 7, 2007, 5),
(388, 7, 2008, 5),
(389, 7, 2020, 5),
(390, 10, 1932, 9),
(391, 10, 1969, 9),
(392, 10, 1972, 9),
(393, 10, 1973, 9),
(394, 10, 1974, 9),
(395, 10, 1980, 9),
(396, 10, 1981, 9),
(397, 10, 1985, 9),
(398, 10, 1986, 9),
(399, 10, 1987, 9),
(400, 10, 1989, 9),
(401, 10, 1990, 9),
(402, 10, 1994, 9),
(403, 10, 1997, 9),
(404, 10, 1999, 9),
(405, 10, 2000, 9),
(406, 10, 2001, 9),
(407, 10, 2003, 9),
(408, 10, 2005, 9),
(409, 10, 2006, 9),
(410, 10, 2008, 9),
(411, 10, 2010, 9),
(412, 10, 1974, 3),
(413, 10, 1975, 3),
(414, 10, 1976, 3),
(415, 10, 2001, 3),
(416, 9, 1977, 3),
(417, 9, 1978, 3),
(418, 9, 1981, 3),
(419, 9, 1984, 3),
(420, 9, 2019, 7),
(421, 9, 2006, 8),
(422, 9, 2001, 8),
(423, 9, 1992, 8),
(424, 9, 1989, 8),
(425, 9, 1986, 8),
(426, 9, 1974, 8),
(427, 9, 1965, 8),
(428, 32, 1937, 7),
(429, 32, 1968, 7),
(431, 4, 2025, 1),
(432, 14, 2025, 2),
(433, 35, 2025, 3),
(434, 9, 2025, 7);

-- --------------------------------------------------------

--
-- Struttura della tabella `utenteregistrato`
--

CREATE TABLE `utenteregistrato` (
  `email` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `nomeUtente` varchar(30) NOT NULL,
  `dataNascita` date NOT NULL,
  `ruolo` varchar(10) NOT NULL DEFAULT 'utente',
  `password` varchar(255) NOT NULL,
  `domandaSicurezza` varchar(200) NOT NULL,
  `rispostaSicurezza` varchar(100) NOT NULL,
  `dataRegistrazione` datetime DEFAULT current_timestamp(),
  `squadraCuore` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utenteregistrato`
--

INSERT INTO `utenteregistrato` (`email`, `nome`, `cognome`, `nomeUtente`, `dataNascita`, `ruolo`, `password`, `domandaSicurezza`, `rispostaSicurezza`, `dataRegistrazione`, `squadraCuore`) VALUES
('claudione04@gmail.com', 'Mario', 'Rossi', 'UniqueUser1769551130517', '1990-01-01', 'utente', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'Domanda', 'Risposta', '2026-01-27 22:58:51', 'Inter'),
('domelap@gmail.com', 'Domenico', 'La Pastina', 'dome04', '2004-09-27', 'utente', '01fe0cfb763297e385ce528f406972e42b280256812f1a18c045ae15e1abb1a3', 'Come ti chiami?', 'Domenico', '2026-01-27 23:16:09', 'Zeta Milano'),
('ESGFSDRGFREGR@EASFESF.IT', 'Luca', 'Mediatore', 'sdargedrgrgedr', '2026-01-09', 'utente', 'dd38f26e07b247ec16edc9d677f7296e8e6dd82b75a4d6386f993c1b66b72b66', 'asfe', 'CIAO', '2026-01-27 11:23:15', 'SIUM'),
('lmediatore@gmail.com', 'Luca', 'Mediatore', 'lucamed', '2004-06-10', 'admin', '9197c389536d5a2f8035fefe9dcea59b8c80aa01e3c47c1905e11a710bcfd933', 'Come ti chiami?', 'Luca', '2026-01-24 11:47:15', 'Napoli'),
('sciaccone04@gmail.com', 'Claudio', 'Sciacca', 'claudio04', '2004-06-14', 'utente', '11c3a389693b96a0128321371f3c4944b27fa598c9dfd15c7b555162f77211ad', 'Come ti chiami?', 'Claudio', '2026-01-24 11:49:37', 'Napoli'),
('test@example.com', 'Mario', 'Rossi', 'utenteDuplicato', '1990-01-01', 'utente', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'Domanda', 'Risposta', '2026-01-27 22:55:38', ''),
('user1769550933753@example.com', 'Mario', 'Rossi', 'user1769550933754', '1995-01-01', 'utente', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'Nome del cane', 'Fido', '2026-01-27 22:55:35', 'Napoli'),
('user1769551021701@example.com', 'Mario', 'Rossi', 'user1769551021702', '1995-01-01', 'utente', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'Nome del cane', 'Fido', '2026-01-27 22:57:03', 'Napoli'),
('user1769551125959@example.com', 'Mario', 'Rossi', 'user1769551125960', '1995-01-01', 'utente', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'Nome del cane', 'Fido', '2026-01-27 22:58:47', 'Napoli'),
('user1769551210677@example.com', 'Mario', 'Rossi', 'user1769551210678', '1995-01-01', 'utente', '008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601', 'Nome del cane', 'Fido', '2026-01-27 23:00:12', 'Napoli');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `amministratore`
--
ALTER TABLE `amministratore`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `nomeUtente` (`nomeUtente`);

--
-- Indici per le tabelle `calciatore`
--
ALTER TABLE `calciatore`
  ADD PRIMARY KEY (`idCalciatore`),
  ADD UNIQUE KEY `idx_unique_calciatore` (`nome`,`cognome`,`ruolo`,`nazionalita`);

--
-- Indici per le tabelle `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`idClub`);

--
-- Indici per le tabelle `commento`
--
ALTER TABLE `commento`
  ADD PRIMARY KEY (`idCommento`),
  ADD KEY `idTopic` (`idTopic`);

--
-- Indici per le tabelle `competizione`
--
ALTER TABLE `competizione`
  ADD PRIMARY KEY (`idCompetizione`);

--
-- Indici per le tabelle `preferito`
--
ALTER TABLE `preferito`
  ADD PRIMARY KEY (`idPreferito`),
  ADD KEY `email` (`email`);

--
-- Indici per le tabelle `rosastagionale`
--
ALTER TABLE `rosastagionale`
  ADD PRIMARY KEY (`idRosaStagionale`),
  ADD UNIQUE KEY `idx_unique_rosa` (`idClub`,`idCalciatore`,`stagione`),
  ADD KEY `idCalciatore` (`idCalciatore`);

--
-- Indici per le tabelle `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`idTopic`);

--
-- Indici per le tabelle `trofeo`
--
ALTER TABLE `trofeo`
  ADD PRIMARY KEY (`idTrofeo`),
  ADD KEY `idClub` (`idClub`),
  ADD KEY `idCompetizione` (`idCompetizione`);

--
-- Indici per le tabelle `utenteregistrato`
--
ALTER TABLE `utenteregistrato`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `nomeUtente` (`nomeUtente`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `calciatore`
--
ALTER TABLE `calciatore`
  MODIFY `idCalciatore` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=244;

--
-- AUTO_INCREMENT per la tabella `club`
--
ALTER TABLE `club`
  MODIFY `idClub` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT per la tabella `commento`
--
ALTER TABLE `commento`
  MODIFY `idCommento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT per la tabella `competizione`
--
ALTER TABLE `competizione`
  MODIFY `idCompetizione` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT per la tabella `preferito`
--
ALTER TABLE `preferito`
  MODIFY `idPreferito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT per la tabella `rosastagionale`
--
ALTER TABLE `rosastagionale`
  MODIFY `idRosaStagionale` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=233;

--
-- AUTO_INCREMENT per la tabella `topic`
--
ALTER TABLE `topic`
  MODIFY `idTopic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT per la tabella `trofeo`
--
ALTER TABLE `trofeo`
  MODIFY `idTrofeo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=436;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `commento`
--
ALTER TABLE `commento`
  ADD CONSTRAINT `commento_ibfk_1` FOREIGN KEY (`idTopic`) REFERENCES `topic` (`idTopic`) ON DELETE CASCADE;

--
-- Limiti per la tabella `preferito`
--
ALTER TABLE `preferito`
  ADD CONSTRAINT `preferito_ibfk_1` FOREIGN KEY (`email`) REFERENCES `utenteregistrato` (`email`) ON DELETE CASCADE;

--
-- Limiti per la tabella `rosastagionale`
--
ALTER TABLE `rosastagionale`
  ADD CONSTRAINT `rosastagionale_ibfk_1` FOREIGN KEY (`idClub`) REFERENCES `club` (`idClub`),
  ADD CONSTRAINT `rosastagionale_ibfk_2` FOREIGN KEY (`idCalciatore`) REFERENCES `calciatore` (`idCalciatore`);

--
-- Limiti per la tabella `trofeo`
--
ALTER TABLE `trofeo`
  ADD CONSTRAINT `trofeo_ibfk_1` FOREIGN KEY (`idClub`) REFERENCES `club` (`idClub`),
  ADD CONSTRAINT `trofeo_ibfk_2` FOREIGN KEY (`idCompetizione`) REFERENCES `competizione` (`idCompetizione`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
