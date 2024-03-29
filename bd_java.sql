USE [master]
GO
/****** Object:  Database [Ludo]    Script Date: 24/03/2024 11:12:28 ******/
CREATE DATABASE [Ludo]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Ludo', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\Ludo.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Ludo_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\Ludo_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
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
/****** Object:  Table [dbo].[adherent]    Script Date: 24/03/2024 11:12:28 ******/
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
	[caution] [int] NULL,
	[observations] [varchar](max) NULL,
 CONSTRAINT [PK__adherent__1048EFC3EA9EA3C6] PRIMARY KEY CLUSTERED 
(
	[id_adherent] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[emprunt]    Script Date: 24/03/2024 11:12:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[emprunt](
	[id_adherent] [int] NOT NULL,
	[id_jeu] [int] NOT NULL,
	[date_emprunte] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_adherent] ASC,
	[id_jeu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[jeu]    Script Date: 24/03/2024 11:12:28 ******/
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
	[duree_max] [int] NULL,
	[descriptif] [varchar](50) NULL,
	[editeur] [varchar](50) NULL,
	[disponible] [tinyint] NULL,
	[etat] [varchar](50) NULL,
	[caution] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_jeu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[personnel]    Script Date: 24/03/2024 11:12:28 ******/
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
PRIMARY KEY CLUSTERED 
(
	[id_personnel] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[personnes_associes]    Script Date: 24/03/2024 11:12:28 ******/
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
/****** Object:  Table [dbo].[user_credentials]    Script Date: 24/03/2024 11:12:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_credentials](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_adherent] [int] NULL,
	[id_personnel] [int] NULL,
	[hashValue] [varbinary](8000) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[emprunt]  WITH CHECK ADD  CONSTRAINT [FK__emprunt__id_adhe__3F466844] FOREIGN KEY([id_adherent])
REFERENCES [dbo].[adherent] ([id_adherent])
GO
ALTER TABLE [dbo].[emprunt] CHECK CONSTRAINT [FK__emprunt__id_adhe__3F466844]
GO
ALTER TABLE [dbo].[emprunt]  WITH CHECK ADD FOREIGN KEY([id_jeu])
REFERENCES [dbo].[jeu] ([id_jeu])
GO
ALTER TABLE [dbo].[personnes_associes]  WITH CHECK ADD  CONSTRAINT [FK_personnes_associes_adherent] FOREIGN KEY([id_adherent])
REFERENCES [dbo].[adherent] ([id_adherent])
GO
ALTER TABLE [dbo].[personnes_associes] CHECK CONSTRAINT [FK_personnes_associes_adherent]
GO
ALTER TABLE [dbo].[user_credentials]  WITH CHECK ADD  CONSTRAINT [FK_user_credentials_adherent] FOREIGN KEY([id_adherent])
REFERENCES [dbo].[adherent] ([id_adherent])
GO
ALTER TABLE [dbo].[user_credentials] CHECK CONSTRAINT [FK_user_credentials_adherent]
GO
ALTER TABLE [dbo].[user_credentials]  WITH CHECK ADD  CONSTRAINT [FK_user_credentials_personnel] FOREIGN KEY([id_personnel])
REFERENCES [dbo].[personnel] ([id_personnel])
GO
ALTER TABLE [dbo].[user_credentials] CHECK CONSTRAINT [FK_user_credentials_personnel]
GO
USE [master]
GO
ALTER DATABASE [Ludo] SET  READ_WRITE 
GO
