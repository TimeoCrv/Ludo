USE [master]
GO
/****** Object:  Database [Ludo]    Script Date: 03/04/2024 12:24:28 ******/
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
/****** Object:  User [ercan]    Script Date: 03/04/2024 12:24:28 ******/
CREATE USER [ercan] FOR LOGIN [ercan] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [ercan]
GO
/****** Object:  Table [dbo].[adherent]    Script Date: 03/04/2024 12:24:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[adherent](
	[id_adherent] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](50) NULL,
	[prenom] [varchar](50) NULL,
	[tel] [nchar](10) NULL,
	[adresse] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[date_inscription] [date] NULL,
	[date_inscription_fin] [date] NULL,
	[numero_carte_identite] [varchar](50) NULL,
	[actif] [tinyint] NULL,
	[caution] [float] NULL,
	[observations] [varchar](max) NULL,
	[password] [varchar](50) NULL,
 CONSTRAINT [PK__adherent__1048EFC3EA9EA3C6] PRIMARY KEY CLUSTERED 
(
	[id_adherent] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[emprunt]    Script Date: 03/04/2024 12:24:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[emprunt](
	[id_adherent] [int] NOT NULL,
	[id_jeu] [int] NOT NULL,
	[date_emprunte] [date] NULL,
	[date_a_rendre] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_adherent] ASC,
	[id_jeu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[jeu]    Script Date: 03/04/2024 12:24:28 ******/
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
	[age_max] [int] NULL,
	[duree_min] [int] NULL,
	[duree_max] [int] NULL,
	[descriptif] [varchar](max) NULL,
	[editeur] [varchar](50) NULL,
	[disponible] [tinyint] NULL,
	[etat] [varchar](50) NULL,
 CONSTRAINT [PK__jeu__D366EF201AD1617C] PRIMARY KEY CLUSTERED 
(
	[id_jeu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[personnel]    Script Date: 03/04/2024 12:24:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[personnel](
	[id_personnel] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](50) NULL,
	[prenom] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[tel] [varchar](50) NULL,
	[adresse] [varchar](50) NULL,
	[role] [smallint] NULL,
	[password] [varchar](50) NULL,
 CONSTRAINT [PK__personne__F333208DC143355E] PRIMARY KEY CLUSTERED 
(
	[id_personnel] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[personnes_associes]    Script Date: 03/04/2024 12:24:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[personnes_associes](
	[id_personnes_associes] [int] IDENTITY(1,1) NOT NULL,
	[nom] [varchar](50) NULL,
	[prenom] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[tel] [varchar](50) NULL,
	[adresse] [varchar](50) NULL,
	[numero_carte_identite] [varchar](50) NULL,
	[id_adherent] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_personnes_associes] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[adherent] ON 

INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (1, N'Dubois', N'Jean', N'0612345678', N'12 Rue de la Liberté, Paris', N'jean.dubois@gmail.com', CAST(N'2023-05-12' AS Date), CAST(N'2024-05-12' AS Date), N'ABC123456789', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (2, N'Lefevre', N'Marie', N'0678901234', N'8 Rue des Roses, Lyon', N'marie.lefevre@yahoo.com', CAST(N'2023-09-20' AS Date), CAST(N'2024-09-20' AS Date), N'XYZ987654321', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (3, N'Martin', N'Pierre', N'0698765432', N'15 Rue du Commerce, Marseille', N'pierre.martin@gmail.com', CAST(N'2023-11-07' AS Date), CAST(N'2024-11-07' AS Date), N'DEF789123456', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (4, N'Thomas', N'Sophie', N'0654321098', N'23 Rue de la Paix, Bordeaux', N'sophie.thomas@freebird.com', CAST(N'2023-03-30' AS Date), CAST(N'2024-03-30' AS Date), N'GHI987654321', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (5, N'Robert', N'Emilie', N'0623456789', N'5 Rue des Champs, Lille', N'emilie.robert@egmail.com', CAST(N'2023-08-14' AS Date), CAST(N'2024-08-14' AS Date), N'JKL123456789', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (6, N'Simon', N'Luc', N'0689012345', N'19 Rue de la République, Toulouse', N'luc.simon@laposte.fr', CAST(N'2023-02-18' AS Date), CAST(N'2024-02-18' AS Date), N'MNO987654321', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (7, N'Mercier', N'Julie', N'0612345678', N'31 Rue Victor Hugo, Nice', N'julie.mercier@gmail.com', CAST(N'2023-10-05' AS Date), CAST(N'2024-10-05' AS Date), N'PQR123456789', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (8, N'Dupont', N'Paul', N'0678901234', N'44 Avenue de Gaulle, Nantes', N'paul.dupont@yahoo.com', CAST(N'2023-06-22' AS Date), CAST(N'2024-06-22' AS Date), N'STU987654321', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (9, N'Moreau', N'Isabelle', N'0698765432', N'27 Boulevard Pasteur, Strasbourg', N'isabelle.moreau@yahoo.com', CAST(N'2023-04-10' AS Date), CAST(N'2024-04-10' AS Date), N'VWX123456789', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (10, N'Lefort', N'Antoine', N'0654321098', N'3 Rue des Fleurs, Rennes', N'antoine.lefort@freebird.com', CAST(N'2023-12-15' AS Date), CAST(N'2024-12-15' AS Date), N'YZA987654321', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (11, N'Dubois', N'Marie', N'0123456789', N'1 Rue de la Liberté, Paris', N'marie.dubois@gmail.com', CAST(N'2023-05-12' AS Date), CAST(N'2024-05-12' AS Date), N'ABC123456789', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (12, N'Lefebvre', N'Jean', N'0987654321', N'2 Rue du Château, Lyon', N'jean.lefebvre@yahoo.com', CAST(N'2023-09-20' AS Date), CAST(N'2024-09-20' AS Date), N'XYZ987654321', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (13, N'Martinez', N'Sophie', N'0654321098', N'3 Rue des Roses, Marseille', N'sophie.martinez@bluebox.com', CAST(N'2023-11-07' AS Date), CAST(N'2024-11-07' AS Date), N'DEF789123456', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (14, N'Bernard', N'Pierre', N'0345678901', N'4 Rue de la Paix, Toulouse', N'pierre.bernard@gmail.com', CAST(N'2023-03-30' AS Date), CAST(N'2024-03-30' AS Date), N'GHI987654321', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (15, N'Thomas', N'Isabelle', N'0456789012', N'5 Avenue Victor Hugo, Bordeaux', N'isabelle.thomas@gamil.com', CAST(N'2023-08-14' AS Date), CAST(N'2024-08-14' AS Date), N'JKL123456789', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (16, N'Petit', N'Philippe', N'0567890123', N'6 Boulevard Saint-Michel, Nantes', N'philippe.petit@yahoo.com', CAST(N'2023-02-18' AS Date), CAST(N'2024-02-18' AS Date), N'MNO987654321', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (17, N'Robert', N'Julie', N'0678901234', N'7 Rue du Commerce, Strasbourg', N'julie.robert@gmail.com', CAST(N'2023-10-05' AS Date), CAST(N'2024-10-05' AS Date), N'PQR123456789', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (18, N'Moreau', N'Nicolas', N'0789012345', N'8 Avenue des Champs-Élysées, Lille', N'nicolas.moreau@freebird.com', CAST(N'2023-06-22' AS Date), CAST(N'2024-06-22' AS Date), N'STU987654321', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (19, N'Alvarez', N'Caroline', N'0890123456', N'9 Rue des Fleurs, Rennes', N'caroline.dubois@gmail.com', CAST(N'2023-04-10' AS Date), CAST(N'2024-04-10' AS Date), N'VWX123456789', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (20, N'Salamanca', N'Luc', N'0901234567', N'10 Rue de la République, Nice', N'luc.salamanca@freebird.com', CAST(N'2023-12-15' AS Date), CAST(N'2024-12-15' AS Date), N'YZA987654321', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (21, N'Ducas', N'Marie', N'0601020304', N'12 Rue de la Paix, Paris', N'marie.ducas@gmail.com', CAST(N'2023-07-28' AS Date), CAST(N'2024-07-28' AS Date), N'1234567890AB', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (22, N'Doyle', N'Antoine', N'0670504030', N'25 Avenue des Champs-Élysées, Paris', N'antoine.doyle@yahoo.com', CAST(N'2023-11-15' AS Date), CAST(N'2024-11-15' AS Date), N'0987654321CD', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (23, N'Noir', N'Sophie', N'0630204010', N'8 Rue de la Liberté, Lyon', N'sophie.noir@gmail.com', CAST(N'2023-09-10' AS Date), CAST(N'2024-09-10' AS Date), N'1357902468EF', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (24, N'Lefebvre', N'Luc', N'0650403020', N'15 Rue Victor Hugo, Marseille', N'luc.lefebvre@yahoo.com', CAST(N'2023-05-05' AS Date), CAST(N'2024-05-05' AS Date), N'2468013579GH', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (25, N'Roux', N'Camille', N'0670706050', N'20 Avenue des Lilas, Bordeaux', N'camille.roux@gmail.com', CAST(N'2023-03-20' AS Date), CAST(N'2024-03-20' AS Date), N'9876543210IJ', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (26, N'Girard', N'Juliette', N'0630102050', N'7 Rue du Commerce, Nice', N'juliette.girard@yahoo.com', CAST(N'2023-08-12' AS Date), CAST(N'2024-08-12' AS Date), N'0123456789KL', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (27, N'Bernard', N'Pierre', N'0670508070', N'10 Avenue Foch, Toulouse', N'pierre.bernard@freebird.com', CAST(N'2023-12-25' AS Date), CAST(N'2024-12-25' AS Date), N'9876543210MN', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (28, N'Petit', N'Emma', N'0603040506', N'30 Rue de la République, Lille', N'emma.petit@yahoo.com', CAST(N'2023-02-14' AS Date), CAST(N'2024-02-14' AS Date), N'4567890123OP', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (29, N'Morat', N'Lucas', N'0670809060', N'5 Avenue du Général de Gaulle, Nantes', N'lucas.moreau@gmail.com', CAST(N'2023-10-30' AS Date), CAST(N'2024-10-30' AS Date), N'7890123456QR', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (30, N'Leroy', N'Manon', N'0630100506', N'18 Rue des Roses, Lyon', N'manon.leroy@gmail.com', CAST(N'2023-06-20' AS Date), CAST(N'2024-06-20' AS Date), N'2345678901ST', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (31, N'Martin', N'Élise', N'0601020304', N'1 Rue de la Paix,Lorient', N'elise.martin@gmail.com', CAST(N'2023-06-12' AS Date), CAST(N'2024-06-12' AS Date), N'ABC123456789', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (32, N'Bernard', N'Antoine', N'0670504030', N'2 Rue des Lilas, Lorient,Lorient', N'antoine.bernard@gmail.com', CAST(N'2023-07-15' AS Date), CAST(N'2024-07-15' AS Date), N'DEF234567890', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (33, N'Dubois', N'Charlotte', N'0690807060', N'3 Avenue Victor Hugo, Lorient', N'charlotte.dubois@gmail.com', CAST(N'2023-08-20' AS Date), CAST(N'2024-08-20' AS Date), N'GHI345678901', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (34, N'Thomas', N'Maxime', N'0609070806', N'4 Avenue Foch, Lorient', N'maxime.thomas@egmail.com', CAST(N'2023-09-25' AS Date), CAST(N'2024-09-25' AS Date), N'JKL456789012', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (35, N'Robert', N'Léa', N'0670605040', N'5 Rue des Roses, Lorient', N'lea.robert@gmail.com', CAST(N'2023-10-01' AS Date), CAST(N'2024-10-01' AS Date), N'MNO567890123', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (36, N'Moreau', N'Luc', N'0630403020', N'6 Boulevard Carnot, Lorient', N'luc.moreau@freebird.com', CAST(N'2023-11-05' AS Date), CAST(N'2024-11-05' AS Date), N'PQR678901234', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (37, N'Petit', N'Manon', N'0690807060', N'7 Rue du Commerce, Lorient', N'manon.petit@gmail.com', CAST(N'2023-12-10' AS Date), CAST(N'2024-12-10' AS Date), N'STU789012345', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (38, N'Durand', N'Nicolas', N'0603020105', N'8 Rue du Paradis, Lorient', N'nicolas.durand@gmail.com', CAST(N'2024-01-15' AS Date), CAST(N'2025-01-15' AS Date), N'VWX890123456', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (39, N'Leroy', N'Emma', N'0670706050', N'9 Avenue des Acacias, Lorient', N'emma.leroy@yahoo.com', CAST(N'2024-02-20' AS Date), CAST(N'2025-02-20' AS Date), N'YZA901234567', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (40, N'Morel', N'Arthur', N'0630403020', N'10 Rue des Champs, Lorient', N'arthur.morel@gmail.com', CAST(N'2024-03-25' AS Date), CAST(N'2025-03-25' AS Date), N'BCD012345678', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (41, N'Lefebvre', N'Juliette', N'0690908070', N'11 Rue du Temple, Lorient', N'juliette.lefebvre@yahoo.com', CAST(N'2024-04-30' AS Date), CAST(N'2025-04-30' AS Date), N'CDE123456789', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (42, N'Garcia', N'Louis', N'0608070603', N'12 Rue de la Gare, Lorient', N'louis.garcia@gmail.com', CAST(N'2024-05-05' AS Date), CAST(N'2025-05-05' AS Date), N'EFG234567890', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (43, N'Fournier', N'Zoé', N'0670605040', N'13 Boulevard Voltaire, Lorient', N'zoe.fournier@gmail.com', CAST(N'2024-06-10' AS Date), CAST(N'2025-06-10' AS Date), N'FGH345678901', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (44, N'Caron', N'Hugo', N'0630201005', N'14 Rue du Moulin, Lorient', N'hugo.caron@yahoo.com', CAST(N'2024-07-15' AS Date), CAST(N'2025-07-15' AS Date), N'GHI456789012', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (45, N'Lemoine', N'Inès', N'0690504030', N'15 Avenue du Bois, Lorient', N'ines.lemoine@gmail.com', CAST(N'2024-08-20' AS Date), CAST(N'2025-08-20' AS Date), N'HIJ567890123', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (46, N'Girard', N'Eva', N'0607080603', N'16 Rue de la Fontaine, Lorient', N'eva.girard@yahoo.com', CAST(N'2024-09-25' AS Date), CAST(N'2025-09-25' AS Date), N'IJK678901234', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (47, N'Roussel', N'Hugo', N'0670302010', N'17 Boulevard des Iris, Lorient', N'hugo.roussel@gmail.com', CAST(N'2024-10-01' AS Date), CAST(N'2025-10-01' AS Date), N'JKL789012345', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (48, N'Colin', N'Lina', N'0630908070', N'18 Rue du Pont, Lorient', N'lina.colin@yahoo.com', CAST(N'2024-11-05' AS Date), CAST(N'2025-11-05' AS Date), N'KLM901234567', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (49, N'Perrin', N'Axel', N'0690706050', N'19 Avenue des Ormes, Lorient', N'axel.perrin@gmail.com', CAST(N'2024-12-10' AS Date), CAST(N'2025-12-10' AS Date), N'LMN012345678', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (50, N'Brun', N'Rose', N'0608040201', N'20 Rue des Pommiers, Lorient', N'rose.brun@yahoo.com', CAST(N'2025-01-15' AS Date), CAST(N'2026-01-15' AS Date), N'MNO123456789', 1, 60, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (51, N'Batum', N'Nicolas', N'0612345678', N'1 Rue de la Liberté, Paris', N'nicolas.batum@gmail.com', NULL, NULL, N'123456789012345', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (52, N'Gobert', N'Rudy', N'0623456789', N'2 Rue de la Paix, Lyon', N'rudy.gobert@gmail.com', NULL, NULL, N'234567890123456', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (53, N'Fournier', N'Evan', N'0634567890', N'3 Avenue des Champs-Élysées, Marseille', N'evan.fournier@gmail.com', NULL, NULL, N'345678901234567', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (54, N'Parker', N'Tony', N'0645678901', N'4 Boulevard Saint-Germain, Bordeaux', N'tony.parker@gmail.com', NULL, NULL, N'456789012345678', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (55, N'Ntilikina', N'Frank', N'0656789012', N'5 Rue du Faubourg Saint-Honoré, Lille', N'frank.ntilikina@gmail.com', NULL, NULL, N'567890123456789', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (56, N'Mahinmi', N'Ian', N'0667890123', N'6 Avenue Montaigne, Toulouse', N'ian.mahinmi@gmail.com', NULL, NULL, N'678901234567890', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (57, N'Noah', N'Joakim', N'0678901234', N'7 Quai des Orfèvres, Nantes', N'joakim.noah@gmail.com', NULL, NULL, N'789012345678901', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (58, N'Diaw', N'Boris', N'0689012345', N'8 Place de la Concorde, Strasbourg', N'boris.diaw@gmail.com', NULL, NULL, N'890123456789012', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (59, N'Gelabale', N'Mickaël', N'0690123456', N'9 Rue Royale, Nice', N'mickael.gelabale@gmail.com', NULL, NULL, N'901234567890123', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (60, N'Diallo', N'Cheick', N'0601234567', N'10 Avenue Foch, Rennes', N'cheick.diallo@gmail.com', NULL, NULL, N'012345678901234', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (61, N'Parker', N'Jabari', N'0612345678', N'1 Rue de la Liberté, Paris', N'jabari.parker@gmail.com', NULL, NULL, N'123456789012345', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (62, N'Walker', N'Kemba', N'0623456789', N'2 Rue de la Paix, Lyon', N'kemba.walker@gmail.com', NULL, NULL, N'234567890123456', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (63, N'Porzingis', N'Kristaps', N'0634567890', N'3 Avenue des Champs-Élysées, Marseille', N'kristaps.porzingis@gmail.com', NULL, NULL, N'345678901234567', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (64, N'Nowitzki', N'Dirk', N'0645678901', N'4 Boulevard Saint-Germain, Bordeaux', N'dirk.nowitzki@gmail.com', NULL, NULL, N'456789012345678', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (65, N'Garnett', N'Kevin', N'0656789012', N'5 Rue du Faubourg Saint-Honoré, Lille', N'kevin.garnett@gmail.com', NULL, NULL, N'567890123456789', NULL, NULL, NULL, NULL)
INSERT [dbo].[adherent] ([id_adherent], [nom], [prenom], [tel], [adresse], [email], [date_inscription], [date_inscription_fin], [numero_carte_identite], [actif], [caution], [observations], [password]) VALUES (66, N'Duncan', N'Tim', N'0667890123', N'6 Avenue Montaigne, Toulouse', N'tim.duncan@gmail.com', NULL, NULL, N'678901234567890', NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[adherent] OFF
GO
SET IDENTITY_INSERT [dbo].[jeu] ON 

INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (1, N'Dominion', 4, 2, 2008, 13, 99, 30, 45, N'Un jeu de construction de deck où les joueurs acquièrent des cartes pour bâtir leur propre royaume et gagner des points de victoire.', N'Rio Grande Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (2, N'Pandemic', 4, 2, 2008, 10, 99, 45, 60, N'Un jeu de coopération où les joueurs travaillent ensemble pour combattre des maladies qui menacent la planète.', N'Z-Man Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (3, N'Terraforming Mars', 5, 1, 2016, 12, 99, 90, 120, N'Un jeu de gestion de ressources où les joueurs incarnent des corporations travaillant ensemble pour rendre Mars habitable.', N'Stronghold Games', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (4, N'Scythe', 7, 1, 2016, 14, 99, 90, 150, N'Un jeu de gestion et de contrôle de territoire où les joueurs dirigent des factions post-apocalyptiques en Europe de l''Est', N'Stonemaier Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (5, N'Azul', 4, 2, 2017, 8, 99, 30, 45, N'Un jeu de pose de tuiles où les joueurs créent de magnifiques motifs de carreaux dans les palais royaux du Portugal.', N'Plan B Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (6, N'Wingspan', 5, 1, 2019, 10, 99, 40, 70, N'Un jeu d''ornithologie où les joueurs collectent des oiseaux, construisent des habitats et marquent des points en fonction de la diversité de leur collection.', N'Stonemaier Games', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (7, N'Gloomhaven', 4, 1, 2017, 12, 99, 60, 120, N'Un jeu de rôle et de combat tactique coopératif où les joueurs explorent un monde de fantasy, combattent des monstres et prennent des décisions qui influencent le cours de l''histoire.', N'Cephalofair Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (8, N'Kingdomino', 4, 2, 2016, 8, 99, 15, 30, N'Un jeu de placement de tuiles où les joueurs construisent leur propre royaume en associant des tuiles de terrain pour marquer des points.', N'Blue Orange Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (9, N'Blood Rage', 5, 2, 2015, 14, 99, 60, 90, N'Un jeu de conquête et de combat viking où les joueurs se battent pour le contrôle des territoires, des ressources et des dieux pour gagner des points de victoire.', N'Cool Mini or Not', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (10, N'Everdell', 4, 1, 2018, 12, 99, 40, 80, N'Un jeu de gestion et de développement de ville où les joueurs incarnent des créatures de la forêt construisant leur propre cité dans un arbre.', N'Starling Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (11, N'Carcassonne', 5, 2, 2000, 8, 99, 30, 45, N'Un jeu de placement de tuiles où les joueurs construisent ensemble le paysage médiéval de la ville de Carcassonne.', N'Hans im Glück', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (12, N'Agricola', 4, 1, 2007, 12, 99, 90, 150, N'Un jeu de gestion agricole où les joueurs doivent gérer leur ferme, cultiver des champs et élever du bétail pour prospérer.', N'Lookout Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (13, N'Pandemic Legacy: Season 1', 4, 2, 2015, 13, 99, 60, 90, N'Une campagne narrative de Pandemic où les joueurs doivent lutter contre des épidémies et prendre des décisions qui auront un impact permanent sur le jeu.', N'Z-Man Games', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (14, N'Terra Mystica', 5, 2, 2012, 12, 99, 60, 150, N'Un jeu de gestion de ressources et de contrôle de territoire où les joueurs incarnent des factions fantastiques cherchant à étendre leur influence sur un monde magique.', N'Feuerland Spiele', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (15, N'Twilight Struggle', 2, 2, 2005, 13, 99, 120, 180, N'Un jeu de stratégie sur la Guerre froide où les joueurs représentent les États-Unis et Union soviétique dans une lutte pour le contrôle mondial.', N'GMT Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (16, N'Brass Birmingham', 4, 2, 2018, 14, 99, 60, 120, N'Un jeu de gestion économique où les joueurs investissent dans des industries, construisent des infrastructures et font du commerce pour prospérer dans Angleterre industrielle.', N'Roxley', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (17, N'Great Western Trail', 4, 2, 2016, 12, 99, 75, 150, N'Un jeu de construction de deck et de gestion de ressources où les joueurs dirigent des convois de bétail à travers Amérique du Nord pour livrer leur cargaison au Texas.', N'Stronghold Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (18, N'Tzolk The Mayan Calendar', 4, 2, 2012, 13, 99, 90, 120, N'Un jeu de stratégie et de planification à long terme où les joueurs dirigent des tribus mayas et construisent des civilisations en utilisant le calendrier Tzolk', N'Czech Games Edition', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (19, N'Mage Knight', 4, 1, 2011, 14, 99, 150, 240, N'Un jeu aventure et de conquête dans un univers fantastique où les joueurs incarnent des mages puissants explorant des terres dangereuses, combattant des monstres et recrutant des alliés.', N'WizKids', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (20, N'Through the Ages A New Story of Civilization', 4, 2, 2015, 14, 99, 120, 240, N'Un jeu de civilisation basé sur les cartes où les joueurs développent leur propre empire, de Antiquité au futur, en gérant des ressources, des technologies et des dirigeants.', N'Czech Games Edition', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (21, N'Spirit Island', 4, 1, 2017, 13, 99, 90, 120, N'Un jeu coopératif où les joueurs incarnent des esprits puissants protégeant leur île contre invasion des colons européens en utilisant des pouvoirs élémentaires.', N'Greater Than Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (22, N'The Castles of Burgundy', 4, 2, 2011, 12, 99, 30, 90, N'Un jeu de placement de tuiles et de gestion de ressources où les joueurs construisent des domaines, élèvent du bétail et développent des villes pour devenir le plus prospère.', N'Ravensburger', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (23, N'Power Grid', 6, 2, 2004, 12, 99, 120, 150, N'Un jeu de gestion énergie où les joueurs construisent des réseaux électriques et achètent des centrales pour alimenter les villes et gagner des revenus.', N'Rio Grande Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (24, N'Brass Lancashire', 4, 2, 2007, 14, 99, 60, 120, N'Une version révisée et rééditée du jeu Brass qui met les joueurs à la tête de manufactures et de réseaux ferroviaires dans le Lancashire pendant la révolution industrielle.', N'Roxley', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (25, N'Wingspan: Oceania Expansion', 5, 1, 2020, 10, 99, 40, 70, N'Une extension pour le jeu Wingspan, introduisant de nouveaux oiseaux et habitats provenant de la région de Océanie.', N'Stonemaier Games', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (26, N'Root', 6, 2, 2018, 10, 99, 60, 90, N'Un jeu asymétrique de conquête et de contrôle de territoire où chaque joueur incarne une faction animale avec des objectifs et des capacités uniques.', N'Leder Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (27, N'Terraforming Mars: Prelude', 5, 1, 2018, 12, 99, 60, 90, N'Une extension pour le jeu Terraforming Mars, ajoutant des préludes qui accélèrent le début du jeu en donnant aux joueurs des avantages initiaux.', N'Stronghold Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (28, N'Gloomhaven: Forgotten Circles', 4, 1, 2019, 12, 99, 30, 120, N'Une extension pour le jeu Gloomhaven, introduisant de nouveaux scénarios, personnages et ennemis pour étendre aventure.', N'Cephalofair Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (29, N'Brass: Birmingham Deluxe Edition', 4, 2, 2019, 14, 99, 60, 120, N'Une édition deluxe du jeu Brass: Birmingham avec des composants améliorés et des ajouts de gameplay pour une expérience plus immersive.', N'Roxley', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (30, N'Wingspan: European Expansion', 5, 1, 2019, 10, 99, 40, 70, N'Une extension pour le jeu Wingspan, ajoutant de nouveaux oiseaux et habitats provenant de la région européenne.', N'Stonemaier Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (31, N'The Crew: The Quest for Planet Nine', 5, 2, 2019, 10, 99, 20, 30, N'Un jeu coopératif de communication et de déduction où les joueurs forment un équipage spatial pour achever des missions et découvrir la mystérieuse Planète Neuf.', N'KOSMOS', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (32, N'Clank!: A Deck-Building Adventure', 4, 2, 2016, 12, 99, 60, 90, N'Un jeu de deck-building où les joueurs incarnent des aventuriers cherchant à piller un donjon rempli de trésors, tout en évitant les dangers et en attirant attention du dragon gardien.', N'Renegade Game Studios', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (33, N'Azul: Summer Pavilion', 4, 2, 2019, 8, 99, 30, 45, N'Une suite du jeu Azul, introduisant de nouvelles tuiles et mécaniques de jeu alors que les joueurs tentent de créer le plus beau pavillon estival.', N'Next Move Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (34, N'Kingdomino: Age of Giants Expansion', 5, 2, 2018, 8, 99, 15, 30, N'Une extension pour le jeu Kingdomino, ajoutant de nouvelles tuiles et des géants qui interagissent avec le jeu de base pour une expérience de jeu enrichie.', N'Blue Orange Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (35, N'Tapestry', 5, 1, 2019, 12, 99, 90, 150, N'Un jeu de civilisation où les joueurs développent leur propre empire à travers les âges, en explorant, en exploitant des ressources et en découvrant de nouvelles technologies.', N'Stonemaier Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (36, N'Arkham Horror The Card Game', 2, 1, 2016, 14, 99, 60, 120, N'Un jeu de cartes coopératif basé sur univers de Lovecraft, où les joueurs incarnent des investigateurs cherchant à élucider des mystères surnaturels et à sauver le monde de horreur.', N'Fantasy Flight Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (37, N'Marvel Champions The Card Game', 4, 1, 2019, 10, 99, 45, 90, N'Un jeu de cartes coopératif où les joueurs endossent le rôle de super-héros Marvel, combattant ensemble les méchants et les menaces qui menacent le monde.', N'Fantasy Flight Games', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (38, N'Wingspan Swift Start', 5, 1, 2021, 10, 99, 40, 70, N'Une version simplifiée du jeu Wingspan, conçue pour les nouveaux joueurs avec des règles plus simples et une expérience de jeu plus rapide.', N'Stonemaier Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (39, N'Root The Underworld Expansion', 6, 2, 2020, 10, 99, 60, 90, N'Une extension pour le jeu Root, introduisant de nouvelles factions et mécaniques de jeu situées dans le monde souterrain de la forêt.', N'Leder Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (40, N'Hadara', 5, 2, 2019, 10, 99, 60, 120, N'Un jeu de civilisation où les joueurs dirigent leur propre civilisation à travers les âges, en développant des technologies, en recrutant des leaders et en construisant des merveilles.', N'Z-Man Games', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (41, N'Wavelength', 10, 2, 2019, 14, 99, 30, 45, N'Un jeu de communication et de déduction où les joueurs devinent où se trouve une cible sur un spectre en fonction des indices donnés par leur coéquipier.', N'Palm Court', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (42, N'The Quacks of Quedlinburg', 4, 2, 2018, 10, 99, 45, 60, N'Un jeu de bag-building où les joueurs sont des alchimistes concoctant des potions dans leur chaudron, en utilisant des ingrédients variés et en évitant les explosions.', N'North Star Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (43, N'Marvel United', 4, 1, 2020, 8, 99, 20, 45, N'Un jeu de plateau coopératif basé sur univers Marvel, où les joueurs incarnent des super-héros travaillant ensemble pour sauver la ville des vilains.', N'CMON Limited', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (44, N'Lost Ruins of Arnak', 4, 1, 2020, 12, 99, 60, 120, N'Un jeu exploration et de deck-building où les joueurs explorent une île mystérieuse, découvrent des ruines anciennes et affrontent des dangers pour découvrir ses secrets.', N'Czech Games Edition', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (45, N'Dixit', 6, 3, 2008, 8, 99, 30, 45, N'Un jeu association images et interprétation où les joueurs doivent trouver la carte qui correspond le mieux à une phrase donnée.', N'Libellud', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (46, N'Tapestry', 5, 1, 2019, 12, 99, 90, 150, N'Un jeu de civilisation où les joueurs développent leur propre société à travers différentes ères en explorant, en innovant et en interagissant avec autres civilisations.', N'Stonemaier Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (47, N'Wavelength', 12, 2, 2019, 14, 99, 30, 45, N'Un jeu de communication et de déduction où les joueurs doivent deviner où se situe un point sur un spectre donné en fonction des indices donnés par leur coéquipier.', N'Palm Court', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (48, N'The Quacks of Quedlinburg', 4, 2, 2018, 10, 99, 45, 60, N'Un jeu de pression de la chance où les joueurs sont des charlatans concoctant des potions magiques pour impressionner le public dans la ville de Quedlinburg.', N'North Star Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (49, N'7 Wonders Duel', 2, 2, 2015, 10, 99, 30, 45, N'Une version pour deux joueurs du jeu 7 Wonders où les joueurs construisent leur propre civilisation en choisissant des cartes dans un draft.', N'Repos Production', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (50, N'Sherlock Holmes Consulting Detective', 8, 1, 1981, 14, 99, 60, 120, N'Un jeu coopératif de résolution de mystères où les joueurs incarnent des détectives travaillant ensemble pour résoudre des affaires en interrogeant des suspects et en recherchant des indices.', N'Ystari Games', 1, N'Nouveau')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (51, N'Potion Explosion', 4, 2, 2015, 8, 99, 30, 45, N'Un jeu de correspondance de billes où les joueurs manipulent des potions magiques et essaient de créer des explosions pour collecter des ingrédients.', N'CMON Limited', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (52, N'Marvel Champions: The Card Game', 4, 1, 2019, 10, 99, 45, 90, N'Un jeu de cartes coopératif dans univers Marvel où les joueurs incarnent des super-héros luttant contre les méchants et résolvant des crises.', N'Fantasy Flight Games', 1, N'Comme neuf')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (53, N'Root: The Underworld Expansion', 6, 2, 2019, 10, 99, 60, 90, N'Une extension pour le jeu Root, introduisant de nouvelles factions et des mécaniques de jeu pour étendre les possibilités stratégiques.', N'Leder Games', 1, N'Bon état')
INSERT [dbo].[jeu] ([id_jeu], [nom], [nombre_joueurs_max], [nombre_joueurs_min], [annee], [age_min], [age_max], [duree_min], [duree_max], [descriptif], [editeur], [disponible], [etat]) VALUES (54, N'Tigris & Euphrates', 4, 2, 1997, 12, 99, 60, 120, N'Un jeu de civilisation et de contrôle de territoire où les joueurs construisent des civilisations anciennes le long des fleuves Tigre et Euphrate et cherchent à étendre leur influence.', N'Hans im Glück', 1, N'Nouveau')
SET IDENTITY_INSERT [dbo].[jeu] OFF
GO
SET IDENTITY_INSERT [dbo].[personnes_associes] ON 

INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (1, N'Dupont', N'Jean', N'jean.dupont@gmail.com', N'0612345678', N'1 Rue de la Liberté, Paris', N'123456789012345', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (2, N'Durand', N'Marie', N'marie.durand@gmail.com', N'0623456789', N'2 Rue de la Paix, Lyon', N'234567890123456', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (3, N'Lefebvre', N'Pierre', N'pierre.lefebvre@gmail.com', N'0634567890', N'3 Avenue des Champs-Élysées, Marseille', N'345678901234567', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (4, N'Dubois', N'Sophie', N'sophie.dubois@gmail.com', N'0645678901', N'4 Boulevard Saint-Germain, Bordeaux', N'456789012345678', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (5, N'Martin', N'Isabelle', N'isabelle.martin@gmail.com', N'0656789012', N'5 Rue du Faubourg Saint-Honoré, Lille', N'567890123456789', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (6, N'Bernard', N'Nicolas', N'nicolas.bernard@gmail.com', N'0667890123', N'6 Avenue Montaigne, Toulouse', N'678901234567890', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (7, N'Thomas', N'Camille', N'camille.thomas@gmail.com', N'0678901234', N'7 Quai des Orfèvres, Nantes', N'789012345678901', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (8, N'Petit', N'Lucie', N'lucie.petit@gmail.com', N'0689012345', N'8 Place de la Concorde, Strasbourg', N'890123456789012', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (9, N'Robert', N'Antoine', N'antoine.robert@gmail.com', N'0690123456', N'9 Rue Royale, Nice', N'901234567890123', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (10, N'Richard', N'Émilie', N'emilie.richard@gmail.com', N'0601234567', N'10 Avenue Foch, Rennes', N'012345678901234', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (11, N'Patrick', N'Sean', N'sean.patrick@gmail.com', N'0612345678', N'1 Rue de la Liberté, Paris', N'123456789012345', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (12, N'Durant', N'Kevin', N'kevin.durant@yahoo.com', N'0623456789', N'2 Rue de la Paix, Lyon', N'234567890123456', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (13, N'Curry', N'Steph', N'steph.curry@gmail.com', N'0634567890', N'3 Avenue des Champs-Élysées, Marseille', N'345678901234567', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (14, N'Thompson', N'Klay', N'klay.thomposn@gmail.com', N'0645678901', N'4 Boulevard Saint-Germain, Bordeaux', N'456789012345678', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (15, N'Bird', N'Larry', N'larry.bird@yahoo.com', N'0656789012', N'5 Rue du Faubourg Saint-Honoré, Lille', N'567890123456789', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (16, N'Johnson', N'Magic', N'magic.johnson@gmail.com', N'0667890123', N'6 Avenue Montaigne, Toulouse', N'678901234567890', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (17, N'James', N'Lebron', N'lebron.james@yahoo.com', N'0678901234', N'7 Quai des Orfèvres, Nantes', N'789012345678901', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (18, N'Townes', N'Karl', N'karl.townes@gmail.com', N'0689012345', N'8 Place de la Concorde, Strasbourg', N'890123456789012', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (19, N'Ball', N'LaMelo', N'lamelo.ball@yahoo.com', N'0690123456', N'9 Rue Royale, Nice', N'901234567890123', NULL)
INSERT [dbo].[personnes_associes] ([id_personnes_associes], [nom], [prenom], [email], [tel], [adresse], [numero_carte_identite], [id_adherent]) VALUES (20, N'Jordan', N'Micheal', N'micheal.jordan@gmail.com', N'0601234567', N'10 Avenue Foch, Rennes', N'012345678901234', NULL)
SET IDENTITY_INSERT [dbo].[personnes_associes] OFF
GO
ALTER TABLE [dbo].[emprunt]  WITH CHECK ADD  CONSTRAINT [FK__emprunt__id_adhe__3F466844] FOREIGN KEY([id_adherent])
REFERENCES [dbo].[adherent] ([id_adherent])
GO
ALTER TABLE [dbo].[emprunt] CHECK CONSTRAINT [FK__emprunt__id_adhe__3F466844]
GO
ALTER TABLE [dbo].[emprunt]  WITH CHECK ADD  CONSTRAINT [FK__emprunt__id_jeu__403A8C7D] FOREIGN KEY([id_jeu])
REFERENCES [dbo].[jeu] ([id_jeu])
GO
ALTER TABLE [dbo].[emprunt] CHECK CONSTRAINT [FK__emprunt__id_jeu__403A8C7D]
GO
ALTER TABLE [dbo].[personnes_associes]  WITH CHECK ADD  CONSTRAINT [FK_personnes_associes_adherent] FOREIGN KEY([id_adherent])
REFERENCES [dbo].[adherent] ([id_adherent])
GO
ALTER TABLE [dbo].[personnes_associes] CHECK CONSTRAINT [FK_personnes_associes_adherent]
GO
USE [master]
GO
ALTER DATABASE [Ludo] SET  READ_WRITE 
GO
