USE [master]
GO
/****** Object:  Database [Ludo]    Script Date: 15/05/2024 22:11:10 ******/
CREATE DATABASE [Ludo]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Ludo', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS01\MSSQL\DATA\Ludo.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Ludo_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS01\MSSQL\DATA\Ludo_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [Ludo] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Ludo].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Ludo] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Ludo] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Ludo] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Ludo] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Ludo] SET ARITHABORT OFF 
GO
ALTER DATABASE [Ludo] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Ludo] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Ludo] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Ludo] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Ludo] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Ludo] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Ludo] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Ludo] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Ludo] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Ludo] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Ludo] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Ludo] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Ludo] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Ludo] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Ludo] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Ludo] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Ludo] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Ludo] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Ludo] SET  MULTI_USER 
GO
ALTER DATABASE [Ludo] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Ludo] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Ludo] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Ludo] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Ludo] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Ludo] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Ludo] SET QUERY_STORE = ON
GO
ALTER DATABASE [Ludo] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Ludo]
GO
/****** Object:  User [ercan]    Script Date: 15/05/2024 22:11:11 ******/
CREATE USER [ercan] FOR LOGIN [ercan] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [ercan]
GO
/****** Object:  Table [dbo].[emprunt]    Script Date: 15/05/2024 22:11:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[emprunt](
	[id_profil] [int] NOT NULL,
	[id_jeu_physique] [int] NOT NULL,
	[date_emprunte] [date] NULL,
	[date_a_rendre] [date] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[jeu]    Script Date: 15/05/2024 22:11:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[jeu](
	[id_jeu] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](50) NULL,
	[nombre_joueurs_max] [int] NULL,
	[nombre_joueurs_min] [int] NULL,
	[annee] [int] NULL,
	[age_min] [int] NULL,
	[duree_min] [int] NULL,
	[descriptif] [varchar](max) NULL,
	[editeur] [varchar](50) NULL,
	[disponible] [tinyint] NULL,
	[nombre] [int] NULL,
 CONSTRAINT [PK_jeu] PRIMARY KEY CLUSTERED 
(
	[id_jeu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[jeu_physique]    Script Date: 15/05/2024 22:11:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[jeu_physique](
	[id_jeu_physique] [int] IDENTITY(1,1) NOT NULL,
	[etat] [varchar](50) NOT NULL,
	[id_jeu] [int] NULL,
 CONSTRAINT [PK_jeu_physique] PRIMARY KEY CLUSTERED 
(
	[id_jeu_physique] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[personne_associe]    Script Date: 15/05/2024 22:11:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[personne_associe](
	[id_personne_associe] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](50) NULL,
	[prenom] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[tel] [varchar](50) NULL,
	[adresse] [varchar](50) NULL,
	[numero_carte_identite] [varchar](50) NULL,
	[id_profil] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_personne_associe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[personnel]    Script Date: 15/05/2024 22:11:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[personnel](
	[id_personnel] [int] NOT NULL,
	[nom] [varchar](50) NULL,
	[prenom] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[tel] [varchar](50) NULL,
	[adresse] [varchar](50) NULL,
	[isAdmin] [tinyint] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[profil]    Script Date: 15/05/2024 22:11:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[profil](
	[id_profil] [int] NOT NULL,
	[nom] [varchar](50) NULL,
	[prenom] [varchar](50) NULL,
	[tel] [nchar](10) NULL,
	[adresse] [varchar](50) NULL,
	[date_inscription] [date] NULL,
	[date_inscription_fin] [date] NULL,
	[numero_carte_identite] [varchar](50) NULL,
	[actif] [tinyint] NULL,
	[caution] [float] NULL,
	[observations] [varchar](max) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[utilisateur]    Script Date: 15/05/2024 22:11:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[utilisateur](
	[id_utilisateur] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[salt] [varchar](255) NOT NULL,
	[role] [varchar](50) NULL,
 CONSTRAINT [PK_utilisateur] PRIMARY KEY CLUSTERED 
(
	[id_utilisateur] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[personnel] ([id_personnel], [nom], [prenom], [email], [tel], [adresse], [isAdmin]) VALUES (1, N'admin', N'admin', N'admin@betton.fr', N'0635871964', N'betton', 1)
GO
INSERT [dbo].[profil] ([id_profil], [nom], [prenom], [tel], [adresse], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations]) VALUES (2, N'Dupont', N'Marie', N'0632457891', N'5 Rue des Lilas, 35830 Betton', CAST(N'2024-05-15' AS Date), CAST(N'2025-05-15' AS Date), N'1234567890', 1, 40, N'')
INSERT [dbo].[profil] ([id_profil], [nom], [prenom], [tel], [adresse], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations]) VALUES (3, N'Leclerc', N'Pierre', N'0678912345', N'12 Rue de la Paix, 35830 Betton', CAST(N'2024-05-15' AS Date), CAST(N'2025-05-15' AS Date), N'0987654321', 1, 40, N'')
INSERT [dbo].[profil] ([id_profil], [nom], [prenom], [tel], [adresse], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations]) VALUES (4, N'Martin', N'Sophie', N'0612345678', N'8 Rue du Moulin, 35830 Betton', CAST(N'2024-05-15' AS Date), CAST(N'2025-05-15' AS Date), N'1357924680', 1, 40, N'')
INSERT [dbo].[profil] ([id_profil], [nom], [prenom], [tel], [adresse], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations]) VALUES (5, N'Dubois', N'Jean', N'0698765432', N'15 Rue Victor Hugo, 35830 Betton', CAST(N'2024-05-15' AS Date), CAST(N'2025-05-15' AS Date), N'2468013579', 1, 40, N'')
INSERT [dbo].[profil] ([id_profil], [nom], [prenom], [tel], [adresse], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations]) VALUES (6, N'Moreau', N'Emma', N'0687654321', N'20 Rue des Cerisiers, 35830 Betton', CAST(N'2024-05-15' AS Date), CAST(N'2025-05-15' AS Date), N'9876543210', 1, 40, N'')
INSERT [dbo].[profil] ([id_profil], [nom], [prenom], [tel], [adresse], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations]) VALUES (7, N'Laurent', N'Thomas', N'0654321789', N'25 Rue des Écoles, 35830 Betton', CAST(N'2024-05-15' AS Date), CAST(N'2025-05-15' AS Date), N'3692581470', 1, 40, N'')
INSERT [dbo].[profil] ([id_profil], [nom], [prenom], [tel], [adresse], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations]) VALUES (8, N'Lefebvre', N'Camille', N'0678912345', N'30 Rue du Stade, 35830 Betton', CAST(N'2024-05-15' AS Date), CAST(N'2025-05-15' AS Date), N'6543210987', 1, 40, N'')
INSERT [dbo].[profil] ([id_profil], [nom], [prenom], [tel], [adresse], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations]) VALUES (9, N'Roux', N'Lucas', N'0632147859', N'35 Rue de la Liberté, 35830 Betton', CAST(N'2024-05-15' AS Date), CAST(N'2025-05-15' AS Date), N'9870123456', 1, 40, N'')
INSERT [dbo].[profil] ([id_profil], [nom], [prenom], [tel], [adresse], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations]) VALUES (10, N'Girard', N'Manon', N'0612789456', N'40 Rue du Commerce, 35830 Betton', CAST(N'2024-05-15' AS Date), CAST(N'2025-05-15' AS Date), N'3210456789', 1, 40, N'')
INSERT [dbo].[profil] ([id_profil], [nom], [prenom], [tel], [adresse], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations]) VALUES (11, N'Fontaine', N'Hugo', N'0678451239', N'45 Rue des Roses, 35830 Betton', CAST(N'2024-05-15' AS Date), CAST(N'2025-05-15' AS Date), N'4567890123', 1, 40, N'')
GO
SET IDENTITY_INSERT [dbo].[utilisateur] ON 

INSERT [dbo].[utilisateur] ([id_utilisateur], [email], [password], [salt], [role]) VALUES (1, N'admin@betton.fr', N'wlNljH6W3VF/0GOkv4I3HLH/7u4nwQOvdU4JvEf9F+E=', N'nxbylyn8fpAdKfrIrpB7sQGPwzaX6X', N'admin')
INSERT [dbo].[utilisateur] ([id_utilisateur], [email], [password], [salt], [role]) VALUES (2, N'marie.dupont@example.com', N'4jKxcH7NBBj41Mb2QV6o8sQ+9HD9X6w+hlajG+kV4oQ=', N'UMqXrDbJPJwf2JjboUVBADHO12XEej', N'adherent')
INSERT [dbo].[utilisateur] ([id_utilisateur], [email], [password], [salt], [role]) VALUES (3, N'pierre.leclerc@example.com', N'MmPVlQpCygZKKS8JqDf8De7mILW54q9OV94ermmN8NA=', N'6fpUfnmj6ihW9jvoTqOX7Qwn6cMfKi', N'adherent')
INSERT [dbo].[utilisateur] ([id_utilisateur], [email], [password], [salt], [role]) VALUES (4, N'sophie.martin@example.com', N'kYlTFOn4/nTonCONB4P6D+ZQm2GTgI7Lv0fGpj1X438=', N'j5Ox9dCeHIigQnSeXA2kFvAgLwSudB', N'adherent')
INSERT [dbo].[utilisateur] ([id_utilisateur], [email], [password], [salt], [role]) VALUES (5, N'jean.dubois@example.com', N'PPSxz+EggscVodvowowHkvnm6nQV09wsuQylYlXuLJg=', N'603aQiEpROKkli1PQobRFyCMZcTaOu', N'adherent')
INSERT [dbo].[utilisateur] ([id_utilisateur], [email], [password], [salt], [role]) VALUES (6, N'emma.moreau@example.com', N'CgUyU/Zo/qBNvDdy0Vye4LJ60tsdxdqZqYo+D+hWw4k=', N'mhDKE4WS0ismpei6D44XKK2xWY1S8o', N'adherent')
INSERT [dbo].[utilisateur] ([id_utilisateur], [email], [password], [salt], [role]) VALUES (7, N'thomas.laurent@example.com', N'IDFDZepYQB6lWHCI3W/oZG3Awk8B5GtKktJ65eQ3KuQ=', N'tM5Asvn7P5kcBv6q7ojEsSkDRD034x', N'adherent')
INSERT [dbo].[utilisateur] ([id_utilisateur], [email], [password], [salt], [role]) VALUES (8, N'camille.lefebvre@example.com', N'9NqRPJ0QLcDXh6E6gyykdW1jzXVSJzcObHNtNMJ/VQs=', N'sF2fX0YwqpnKwXxktFsuHjgrTplu9q', N'adherent')
INSERT [dbo].[utilisateur] ([id_utilisateur], [email], [password], [salt], [role]) VALUES (9, N'lucas.roux@example.com', N'q6rm4zsaxsyyXXgc9Sf/NafjR68DxMYpR/c0HjIUzyI=', N'0d3ZkZFKozRHNwpryvHuzTT1iMSGM0', N'adherent')
INSERT [dbo].[utilisateur] ([id_utilisateur], [email], [password], [salt], [role]) VALUES (10, N'manon.girard@example.com', N'g1/kDuqZnw10iq9siWUr4skrxl6+/epWgf5nvjrDqVs=', N'tJd8WNNt1ONCYRVnW82txAm01K9Nd0', N'adherent')
INSERT [dbo].[utilisateur] ([id_utilisateur], [email], [password], [salt], [role]) VALUES (11, N'hugo.fontaine@example.com', N'6MEt6cQh/qvQPCIN9XL8xMSVj2jLfn/xE3YE0ZFDY/I=', N'AlmbasHlCvzOzsJ2417LEVpww1W1p5', N'adherent')
SET IDENTITY_INSERT [dbo].[utilisateur] OFF
GO
/****** Object:  Index [IX_emprunt_1]    Script Date: 15/05/2024 22:11:11 ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_emprunt_1] ON [dbo].[emprunt]
(
	[id_profil] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_emprunt_2]    Script Date: 15/05/2024 22:11:11 ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_emprunt_2] ON [dbo].[emprunt]
(
	[id_jeu_physique] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_jeu_physique]    Script Date: 15/05/2024 22:11:11 ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_jeu_physique] ON [dbo].[jeu_physique]
(
	[id_jeu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_profil]    Script Date: 15/05/2024 22:11:11 ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_profil] ON [dbo].[profil]
(
	[id_profil] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[personnel] ADD  CONSTRAINT [DF_personnel_isAdmin]  DEFAULT ((0)) FOR [isAdmin]
GO
ALTER TABLE [dbo].[emprunt]  WITH CHECK ADD  CONSTRAINT [FK_emprunt_jeu_physique] FOREIGN KEY([id_jeu_physique])
REFERENCES [dbo].[jeu_physique] ([id_jeu_physique])
GO
ALTER TABLE [dbo].[emprunt] CHECK CONSTRAINT [FK_emprunt_jeu_physique]
GO
ALTER TABLE [dbo].[emprunt]  WITH CHECK ADD  CONSTRAINT [FK_emprunt_profil] FOREIGN KEY([id_profil])
REFERENCES [dbo].[profil] ([id_profil])
GO
ALTER TABLE [dbo].[emprunt] CHECK CONSTRAINT [FK_emprunt_profil]
GO
ALTER TABLE [dbo].[jeu_physique]  WITH CHECK ADD  CONSTRAINT [FK_jeu_physique_jeu] FOREIGN KEY([id_jeu])
REFERENCES [dbo].[jeu] ([id_jeu])
GO
ALTER TABLE [dbo].[jeu_physique] CHECK CONSTRAINT [FK_jeu_physique_jeu]
GO
ALTER TABLE [dbo].[personne_associe]  WITH CHECK ADD  CONSTRAINT [FK_personne_associe_profil] FOREIGN KEY([id_profil])
REFERENCES [dbo].[profil] ([id_profil])
GO
ALTER TABLE [dbo].[personne_associe] CHECK CONSTRAINT [FK_personne_associe_profil]
GO
ALTER TABLE [dbo].[personnel]  WITH CHECK ADD  CONSTRAINT [FK_personnel_utilisateur] FOREIGN KEY([id_personnel])
REFERENCES [dbo].[utilisateur] ([id_utilisateur])
GO
ALTER TABLE [dbo].[personnel] CHECK CONSTRAINT [FK_personnel_utilisateur]
GO
ALTER TABLE [dbo].[profil]  WITH CHECK ADD  CONSTRAINT [FK_profil_utilisateur] FOREIGN KEY([id_profil])
REFERENCES [dbo].[utilisateur] ([id_utilisateur])
GO
ALTER TABLE [dbo].[profil] CHECK CONSTRAINT [FK_profil_utilisateur]
GO
USE [master]
GO
ALTER DATABASE [Ludo] SET  READ_WRITE 
GO
