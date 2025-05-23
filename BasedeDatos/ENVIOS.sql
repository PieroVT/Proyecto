USE [master]
GO
/****** Object:  Database [ENVIOS]    Script Date: 18/05/2025 10:47:28 ******/
CREATE DATABASE [ENVIOS]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Shop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Shop.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Shop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Shop_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [ENVIOS] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ENVIOS].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ENVIOS] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ENVIOS] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ENVIOS] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ENVIOS] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ENVIOS] SET ARITHABORT OFF 
GO
ALTER DATABASE [ENVIOS] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ENVIOS] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ENVIOS] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ENVIOS] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ENVIOS] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ENVIOS] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ENVIOS] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ENVIOS] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ENVIOS] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ENVIOS] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ENVIOS] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ENVIOS] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ENVIOS] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ENVIOS] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ENVIOS] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ENVIOS] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ENVIOS] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ENVIOS] SET RECOVERY FULL 
GO
ALTER DATABASE [ENVIOS] SET  MULTI_USER 
GO
ALTER DATABASE [ENVIOS] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ENVIOS] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ENVIOS] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ENVIOS] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [ENVIOS] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'ENVIOS', N'ON'
GO
USE [ENVIOS]
GO
/****** Object:  Table [dbo].[Articulo]    Script Date: 18/05/2025 10:47:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Articulo](
	[art_codigo] [char](36) NOT NULL,
	[art_description] [varchar](200) NULL,
	[art_precio] [decimal](10, 3) NULL,
	[art_estcod] [int] NOT NULL,
	[cat_codigo] [char](36) NULL,
PRIMARY KEY CLUSTERED 
(
	[art_codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Categoria]    Script Date: 18/05/2025 10:47:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Categoria](
	[cat_codigo] [char](36) NOT NULL,
	[cat_descri] [varchar](100) NULL,
	[cat_estcod] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[cat_codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[cliente]    Script Date: 18/05/2025 10:47:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[cliente](
	[cli_codigo] [char](36) NOT NULL,
	[cli_docnum] [varchar](45) NULL,
	[cli_razsoc] [varchar](45) NULL,
	[cli_direc] [varchar](45) NULL,
	[cli_email] [varchar](45) NULL,
	[cli_estcod] [varchar](45) NULL,
PRIMARY KEY CLUSTERED 
(
	[cli_codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[vendedor]    Script Date: 18/05/2025 10:47:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[vendedor](
	[vnd_codigo] [char](36) NOT NULL,
	[vnd_nombres] [varchar](47) NULL,
	[vnd_estcod] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[vnd_codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[venta]    Script Date: 18/05/2025 10:47:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[venta](
	[ven_correl] [char](36) NOT NULL,
	[ven_sunat] [varchar](40) NULL,
	[ven_fecemi] [datetime] NULL,
	[ven_subtotal] [decimal](10, 3) NULL,
	[ven_igv] [decimal](10, 3) NULL,
	[ven_dscto] [decimal](10, 3) NULL,
	[ven_total] [decimal](10, 3) NULL,
	[ven_estcod] [int] NULL,
	[vnd_codigo] [char](36) NULL,
	[cli_codigo] [char](36) NULL,
PRIMARY KEY CLUSTERED 
(
	[ven_correl] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[venta_detalle]    Script Date: 18/05/2025 10:47:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[venta_detalle](
	[ven_correl] [char](36) NOT NULL,
	[art_codigo] [char](36) NOT NULL,
	[ven_precio] [decimal](10, 3) NULL,
	[ven_cantidad] [int] NULL,
	[ven_dsct] [decimal](10, 3) NULL,
	[ven_igv] [decimal](10, 3) NULL,
	[ven_subtotal] [decimal](10, 3) NULL,
PRIMARY KEY CLUSTERED 
(
	[ven_correl] ASC,
	[art_codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'AGF6501                             ', N'XL', CAST(55.000 AS Decimal(10, 3)), 1, N'CAT004                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'AOR4530                             ', N'L', CAST(52.000 AS Decimal(10, 3)), 1, N'CAT001                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'AZG1123                             ', N'XXL', CAST(63.250 AS Decimal(10, 3)), 2, N'CAT005                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'BDO8880                             ', N'M', CAST(35.000 AS Decimal(10, 3)), 2, N'CAT002                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'BHT1100                             ', N'XL', CAST(48.500 AS Decimal(10, 3)), 1, N'CAT004                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'BHT5599                             ', N'S', CAST(30.000 AS Decimal(10, 3)), 1, N'CAT001                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'CNS9910                             ', N'S', CAST(29.500 AS Decimal(10, 3)), 2, N'CAT001                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'DTR3030                             ', N'XXL', CAST(55.000 AS Decimal(10, 3)), 1, N'CAT005                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'EDW2100                             ', N'XL', CAST(59.990 AS Decimal(10, 3)), 1, N'CAT004                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'EUS8099                             ', N'S', CAST(27.990 AS Decimal(10, 3)), 1, N'CAT003                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'FDT4500                             ', N'S', CAST(31.750 AS Decimal(10, 3)), 2, N'CAT001                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'GXR4910                             ', N'L', CAST(45.990 AS Decimal(10, 3)), 1, N'CAT004                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'HTR2023                             ', N'XXL', CAST(50.990 AS Decimal(10, 3)), 1, N'CAT005                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'HUE6678                             ', N'XL', CAST(41.000 AS Decimal(10, 3)), 2, N'CAT004                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'JLO2180                             ', N'XL', CAST(50.500 AS Decimal(10, 3)), 2, N'CAT002                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'JNE3798                             ', N'S', CAST(29.990 AS Decimal(10, 3)), 1, N'CAT001                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'JRT9201                             ', N'M', CAST(37.500 AS Decimal(10, 3)), 2, N'CAT003                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'JXM4310                             ', N'S', CAST(32.500 AS Decimal(10, 3)), 1, N'CAT001                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'JZR1010                             ', N'M', CAST(34.250 AS Decimal(10, 3)), 2, N'CAT002                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'KXA2460                             ', N'S', CAST(28.990 AS Decimal(10, 3)), 1, N'CAT003                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'KZD3300                             ', N'L', CAST(48.250 AS Decimal(10, 3)), 1, N'CAT002                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'LMA7600                             ', N'XXL', CAST(58.000 AS Decimal(10, 3)), 1, N'CAT005                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'LRF3800                             ', N'S', CAST(39.990 AS Decimal(10, 3)), 1, N'CAT003                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'LSF2001                             ', N'S', CAST(38.000 AS Decimal(10, 3)), 1, N'CAT001                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'MJ9843                              ', N'XL', CAST(47.500 AS Decimal(10, 3)), 1, N'CAT004                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'NNW5673                             ', N'XL', CAST(51.000 AS Decimal(10, 3)), 1, N'CAT002                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'NS7503                              ', N'M', CAST(33.750 AS Decimal(10, 3)), 1, N'CAT003                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'NSR6542                             ', N'L', CAST(50.000 AS Decimal(10, 3)), 1, N'CAT001                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'NW035-ST                            ', N'M', CAST(35.500 AS Decimal(10, 3)), 2, N'CAT002                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'PJNE2199                            ', N'XL', CAST(45.000 AS Decimal(10, 3)), 1, N'CAT004                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'PLT5599                             ', N'XXL', CAST(58.750 AS Decimal(10, 3)), 2, N'CAT005                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'PLX4444                             ', N'XL', CAST(54.750 AS Decimal(10, 3)), 2, N'CAT004                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'PMO2130                             ', N'L', CAST(36.000 AS Decimal(10, 3)), 1, N'CAT003                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'QWS7788                             ', N'XXL', CAST(59.990 AS Decimal(10, 3)), 1, N'CAT005                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'RKL5790                             ', N'XXL', CAST(49.990 AS Decimal(10, 3)), 2, N'CAT005                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'RPN8002                             ', N'XXL', CAST(63.000 AS Decimal(10, 3)), 1, N'CAT005                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'RS5100                              ', N'M', CAST(48.000 AS Decimal(10, 3)), 2, N'CAT002                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'RTD8880                             ', N'M', CAST(46.990 AS Decimal(10, 3)), 2, N'CAT002                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'Set268                              ', N'L', CAST(40.000 AS Decimal(10, 3)), 1, N'CAT003                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'SFR9005                             ', N'XXL', CAST(53.000 AS Decimal(10, 3)), 1, N'CAT005                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'UVL2600                             ', N'L', CAST(52.500 AS Decimal(10, 3)), 2, N'CAT002                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'VRW6789                             ', N'M', CAST(38.000 AS Decimal(10, 3)), 2, N'CAT003                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'VRZ2211                             ', N'XL', CAST(54.000 AS Decimal(10, 3)), 1, N'CAT001                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'WFE9980                             ', N'XXL', CAST(56.500 AS Decimal(10, 3)), 2, N'CAT005                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'WZE3300                             ', N'M', CAST(42.000 AS Decimal(10, 3)), 1, N'CAT003                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'XRO8200                             ', N'L', CAST(60.000 AS Decimal(10, 3)), 1, N'CAT004                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'YFL3322                             ', N'L', CAST(47.500 AS Decimal(10, 3)), 2, N'CAT004                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'YSL3300                             ', N'M', CAST(44.000 AS Decimal(10, 3)), 1, N'CAT003                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'ZT4599                              ', N'S', CAST(25.000 AS Decimal(10, 3)), 2, N'CAT001                              ')
INSERT [dbo].[Articulo] ([art_codigo], [art_description], [art_precio], [art_estcod], [cat_codigo]) VALUES (N'ZUP9005                             ', N'L', CAST(41.750 AS Decimal(10, 3)), 2, N'CAT002                              ')
INSERT [dbo].[Categoria] ([cat_codigo], [cat_descri], [cat_estcod]) VALUES (N'CAT001                              ', N'Ropa Occidental', N'ACT')
INSERT [dbo].[Categoria] ([cat_codigo], [cat_descri], [cat_estcod]) VALUES (N'CAT002                              ', N'Conjunto', N'ACT')
INSERT [dbo].[Categoria] ([cat_codigo], [cat_descri], [cat_estcod]) VALUES (N'CAT003                              ', N'Kurta', N'ACT')
INSERT [dbo].[Categoria] ([cat_codigo], [cat_descri], [cat_estcod]) VALUES (N'CAT004                              ', N'Suéter de mangas', N'ACT')
INSERT [dbo].[Categoria] ([cat_codigo], [cat_descri], [cat_estcod]) VALUES (N'CAT005                              ', N'Ropa Tradicional', N'INACT')
INSERT [dbo].[cliente] ([cli_codigo], [cli_docnum], [cli_razsoc], [cli_direc], [cli_email], [cli_estcod]) VALUES (N'CLI001                              ', N'12345678', N'Empresa A', N'Calle Falsa 123', N'empresaA@mail.com', N'ACT')
INSERT [dbo].[cliente] ([cli_codigo], [cli_docnum], [cli_razsoc], [cli_direc], [cli_email], [cli_estcod]) VALUES (N'CLI002                              ', N'23456789', N'Empresa B', N'Av. Principal 456', N'empresaB@mail.com', N'ACT')
INSERT [dbo].[cliente] ([cli_codigo], [cli_docnum], [cli_razsoc], [cli_direc], [cli_email], [cli_estcod]) VALUES (N'CLI003                              ', N'34567890', N'Empresa C', N'Calle Real 789', N'empresaC@mail.com', N'ACT')
INSERT [dbo].[cliente] ([cli_codigo], [cli_docnum], [cli_razsoc], [cli_direc], [cli_email], [cli_estcod]) VALUES (N'CLI004                              ', N'45678901', N'Empresa D', N'Calle 5 101', N'empresaD@mail.com', N'ACT')
INSERT [dbo].[cliente] ([cli_codigo], [cli_docnum], [cli_razsoc], [cli_direc], [cli_email], [cli_estcod]) VALUES (N'CLI005                              ', N'56789012', N'Empresa E', N'Av. 7 202', N'empresaE@mail.com', N'ACT')
INSERT [dbo].[vendedor] ([vnd_codigo], [vnd_nombres], [vnd_estcod]) VALUES (N'VND001                              ', N'Juan Pérez', 1)
INSERT [dbo].[vendedor] ([vnd_codigo], [vnd_nombres], [vnd_estcod]) VALUES (N'VND002                              ', N'María González', 2)
INSERT [dbo].[vendedor] ([vnd_codigo], [vnd_nombres], [vnd_estcod]) VALUES (N'VND003                              ', N'Carlos Díaz', 1)
INSERT [dbo].[vendedor] ([vnd_codigo], [vnd_nombres], [vnd_estcod]) VALUES (N'VND004                              ', N'Ana Martínez', 2)
INSERT [dbo].[vendedor] ([vnd_codigo], [vnd_nombres], [vnd_estcod]) VALUES (N'VND005                              ', N'Luis Rodríguez', 1)
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN001                              ', N'SUNAT001', CAST(N'2025-05-01 00:00:00.000' AS DateTime), CAST(500.000 AS Decimal(10, 3)), CAST(90.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(590.000 AS Decimal(10, 3)), 1, N'VND001                              ', N'CLI001                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN002                              ', N'SUNAT002', CAST(N'2025-05-02 00:00:00.000' AS DateTime), CAST(300.000 AS Decimal(10, 3)), CAST(54.000 AS Decimal(10, 3)), CAST(5.000 AS Decimal(10, 3)), CAST(349.000 AS Decimal(10, 3)), 2, N'VND002                              ', N'CLI002                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN003                              ', N'SUNAT003', CAST(N'2025-05-03 00:00:00.000' AS DateTime), CAST(700.000 AS Decimal(10, 3)), CAST(126.000 AS Decimal(10, 3)), CAST(10.000 AS Decimal(10, 3)), CAST(816.000 AS Decimal(10, 3)), 1, N'VND003                              ', N'CLI003                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN004                              ', N'SUNAT004', CAST(N'2025-05-04 00:00:00.000' AS DateTime), CAST(150.000 AS Decimal(10, 3)), CAST(27.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(177.000 AS Decimal(10, 3)), 2, N'VND004                              ', N'CLI004                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN005                              ', N'SUNAT005', CAST(N'2025-05-05 00:00:00.000' AS DateTime), CAST(250.000 AS Decimal(10, 3)), CAST(45.000 AS Decimal(10, 3)), CAST(5.000 AS Decimal(10, 3)), CAST(290.000 AS Decimal(10, 3)), 1, N'VND005                              ', N'CLI005                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN006                              ', N'SUNAT006', CAST(N'2025-05-06 00:00:00.000' AS DateTime), CAST(200.000 AS Decimal(10, 3)), CAST(36.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(236.000 AS Decimal(10, 3)), 2, N'VND001                              ', N'CLI001                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN007                              ', N'SUNAT007', CAST(N'2025-05-07 00:00:00.000' AS DateTime), CAST(550.000 AS Decimal(10, 3)), CAST(99.000 AS Decimal(10, 3)), CAST(5.000 AS Decimal(10, 3)), CAST(644.000 AS Decimal(10, 3)), 1, N'VND002                              ', N'CLI002                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN008                              ', N'SUNAT008', CAST(N'2025-05-08 00:00:00.000' AS DateTime), CAST(420.000 AS Decimal(10, 3)), CAST(75.600 AS Decimal(10, 3)), CAST(15.000 AS Decimal(10, 3)), CAST(480.000 AS Decimal(10, 3)), 2, N'VND003                              ', N'CLI003                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN009                              ', N'SUNAT009', CAST(N'2025-05-09 00:00:00.000' AS DateTime), CAST(600.000 AS Decimal(10, 3)), CAST(108.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(708.000 AS Decimal(10, 3)), 1, N'VND004                              ', N'CLI004                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN010                              ', N'SUNAT010', CAST(N'2025-05-10 00:00:00.000' AS DateTime), CAST(700.000 AS Decimal(10, 3)), CAST(126.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(826.000 AS Decimal(10, 3)), 1, N'VND005                              ', N'CLI005                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN011                              ', N'SUNAT011', CAST(N'2025-05-11 00:00:00.000' AS DateTime), CAST(450.000 AS Decimal(10, 3)), CAST(81.000 AS Decimal(10, 3)), CAST(5.000 AS Decimal(10, 3)), CAST(526.000 AS Decimal(10, 3)), 2, N'VND001                              ', N'CLI001                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN012                              ', N'SUNAT012', CAST(N'2025-05-12 00:00:00.000' AS DateTime), CAST(500.000 AS Decimal(10, 3)), CAST(90.000 AS Decimal(10, 3)), CAST(10.000 AS Decimal(10, 3)), CAST(580.000 AS Decimal(10, 3)), 1, N'VND002                              ', N'CLI002                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN013                              ', N'SUNAT013', CAST(N'2025-05-13 00:00:00.000' AS DateTime), CAST(350.000 AS Decimal(10, 3)), CAST(63.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(413.000 AS Decimal(10, 3)), 2, N'VND003                              ', N'CLI003                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN014                              ', N'SUNAT014', CAST(N'2025-05-14 00:00:00.000' AS DateTime), CAST(300.000 AS Decimal(10, 3)), CAST(54.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(354.000 AS Decimal(10, 3)), 1, N'VND004                              ', N'CLI004                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN015                              ', N'SUNAT015', CAST(N'2025-05-15 00:00:00.000' AS DateTime), CAST(200.000 AS Decimal(10, 3)), CAST(36.000 AS Decimal(10, 3)), CAST(5.000 AS Decimal(10, 3)), CAST(241.000 AS Decimal(10, 3)), 2, N'VND005                              ', N'CLI005                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN016                              ', N'SUNAT016', CAST(N'2025-05-16 00:00:00.000' AS DateTime), CAST(450.000 AS Decimal(10, 3)), CAST(81.000 AS Decimal(10, 3)), CAST(10.000 AS Decimal(10, 3)), CAST(521.000 AS Decimal(10, 3)), 1, N'VND001                              ', N'CLI001                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN017                              ', N'SUNAT017', CAST(N'2025-05-17 00:00:00.000' AS DateTime), CAST(600.000 AS Decimal(10, 3)), CAST(108.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(708.000 AS Decimal(10, 3)), 2, N'VND002                              ', N'CLI002                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN018                              ', N'SUNAT018', CAST(N'2025-05-18 00:00:00.000' AS DateTime), CAST(350.000 AS Decimal(10, 3)), CAST(63.000 AS Decimal(10, 3)), CAST(5.000 AS Decimal(10, 3)), CAST(408.000 AS Decimal(10, 3)), 1, N'VND003                              ', N'CLI003                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN019                              ', N'SUNAT019', CAST(N'2025-05-19 00:00:00.000' AS DateTime), CAST(500.000 AS Decimal(10, 3)), CAST(90.000 AS Decimal(10, 3)), CAST(5.000 AS Decimal(10, 3)), CAST(590.000 AS Decimal(10, 3)), 2, N'VND004                              ', N'CLI004                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN020                              ', N'SUNAT020', CAST(N'2025-05-20 00:00:00.000' AS DateTime), CAST(700.000 AS Decimal(10, 3)), CAST(126.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(826.000 AS Decimal(10, 3)), 1, N'VND005                              ', N'CLI005                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN021                              ', N'SUNAT021', CAST(N'2025-05-21 00:00:00.000' AS DateTime), CAST(250.000 AS Decimal(10, 3)), CAST(45.000 AS Decimal(10, 3)), CAST(5.000 AS Decimal(10, 3)), CAST(290.000 AS Decimal(10, 3)), 2, N'VND001                              ', N'CLI001                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN022                              ', N'SUNAT022', CAST(N'2025-05-22 00:00:00.000' AS DateTime), CAST(300.000 AS Decimal(10, 3)), CAST(54.000 AS Decimal(10, 3)), CAST(10.000 AS Decimal(10, 3)), CAST(344.000 AS Decimal(10, 3)), 1, N'VND002                              ', N'CLI002                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN023                              ', N'SUNAT023', CAST(N'2025-05-23 00:00:00.000' AS DateTime), CAST(550.000 AS Decimal(10, 3)), CAST(99.000 AS Decimal(10, 3)), CAST(5.000 AS Decimal(10, 3)), CAST(644.000 AS Decimal(10, 3)), 2, N'VND003                              ', N'CLI003                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN024                              ', N'SUNAT024', CAST(N'2025-05-24 00:00:00.000' AS DateTime), CAST(600.000 AS Decimal(10, 3)), CAST(108.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(708.000 AS Decimal(10, 3)), 1, N'VND004                              ', N'CLI004                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN025                              ', N'SUNAT025', CAST(N'2025-05-25 00:00:00.000' AS DateTime), CAST(250.000 AS Decimal(10, 3)), CAST(45.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(295.000 AS Decimal(10, 3)), 2, N'VND005                              ', N'CLI005                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN026                              ', N'SUNAT026', CAST(N'2025-05-26 00:00:00.000' AS DateTime), CAST(420.000 AS Decimal(10, 3)), CAST(75.600 AS Decimal(10, 3)), CAST(10.000 AS Decimal(10, 3)), CAST(485.000 AS Decimal(10, 3)), 1, N'VND001                              ', N'CLI001                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN027                              ', N'SUNAT027', CAST(N'2025-05-27 00:00:00.000' AS DateTime), CAST(350.000 AS Decimal(10, 3)), CAST(63.000 AS Decimal(10, 3)), CAST(5.000 AS Decimal(10, 3)), CAST(408.000 AS Decimal(10, 3)), 2, N'VND002                              ', N'CLI002                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN028                              ', N'SUNAT028', CAST(N'2025-05-28 00:00:00.000' AS DateTime), CAST(200.000 AS Decimal(10, 3)), CAST(36.000 AS Decimal(10, 3)), CAST(5.000 AS Decimal(10, 3)), CAST(241.000 AS Decimal(10, 3)), 1, N'VND003                              ', N'CLI003                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN029                              ', N'SUNAT029', CAST(N'2025-05-29 00:00:00.000' AS DateTime), CAST(700.000 AS Decimal(10, 3)), CAST(126.000 AS Decimal(10, 3)), CAST(0.000 AS Decimal(10, 3)), CAST(826.000 AS Decimal(10, 3)), 2, N'VND004                              ', N'CLI004                              ')
INSERT [dbo].[venta] ([ven_correl], [ven_sunat], [ven_fecemi], [ven_subtotal], [ven_igv], [ven_dscto], [ven_total], [ven_estcod], [vnd_codigo], [cli_codigo]) VALUES (N'VEN030                              ', N'SUNAT030', CAST(N'2025-05-30 00:00:00.000' AS DateTime), CAST(250.000 AS Decimal(10, 3)), CAST(45.000 AS Decimal(10, 3)), CAST(10.000 AS Decimal(10, 3)), CAST(295.000 AS Decimal(10, 3)), 1, N'VND005                              ', N'CLI005                              ')
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN001                              ', N'JNE3798                             ', CAST(29.990 AS Decimal(10, 3)), 15, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(449.850 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN002                              ', N'Set268                              ', CAST(40.000 AS Decimal(10, 3)), 12, CAST(5.000 AS Decimal(10, 3)), NULL, CAST(480.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN003                              ', N'HTR2023                             ', CAST(50.990 AS Decimal(10, 3)), 10, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(509.900 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN004                              ', N'NS7503                              ', CAST(33.750 AS Decimal(10, 3)), 4, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(135.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN005                              ', N'JLO2180                             ', CAST(50.500 AS Decimal(10, 3)), 14, CAST(10.000 AS Decimal(10, 3)), NULL, CAST(705.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN006                              ', N'LRF3800                             ', CAST(39.990 AS Decimal(10, 3)), 11, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(439.890 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN007                              ', N'AOR4530                             ', CAST(52.000 AS Decimal(10, 3)), 16, CAST(5.000 AS Decimal(10, 3)), NULL, CAST(832.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN008                              ', N'PLT5599                             ', CAST(58.750 AS Decimal(10, 3)), 5, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(293.750 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN009                              ', N'WZE3300                             ', CAST(42.000 AS Decimal(10, 3)), 13, CAST(5.000 AS Decimal(10, 3)), NULL, CAST(546.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN010                              ', N'NNW5673                             ', CAST(51.000 AS Decimal(10, 3)), 18, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(918.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN011                              ', N'CNS9910                             ', CAST(29.500 AS Decimal(10, 3)), 9, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(265.500 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN012                              ', N'PMO2130                             ', CAST(36.000 AS Decimal(10, 3)), 8, CAST(5.000 AS Decimal(10, 3)), NULL, CAST(288.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN013                              ', N'SFR9005                             ', CAST(53.000 AS Decimal(10, 3)), 14, CAST(5.000 AS Decimal(10, 3)), NULL, CAST(742.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN014                              ', N'YSL3300                             ', CAST(44.000 AS Decimal(10, 3)), 7, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(308.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN015                              ', N'BHT1100                             ', CAST(48.500 AS Decimal(10, 3)), 11, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(533.500 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN016                              ', N'KXA2460                             ', CAST(28.990 AS Decimal(10, 3)), 10, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(289.900 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN017                              ', N'NSR6542                             ', CAST(50.000 AS Decimal(10, 3)), 17, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(850.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN018                              ', N'AZG1123                             ', CAST(63.250 AS Decimal(10, 3)), 6, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(379.500 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN019                              ', N'VRW6789                             ', CAST(38.000 AS Decimal(10, 3)), 11, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(418.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN020                              ', N'AGF6501                             ', CAST(55.000 AS Decimal(10, 3)), 9, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(495.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN021                              ', N'EUS8099                             ', CAST(27.990 AS Decimal(10, 3)), 12, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(335.880 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN022                              ', N'YFL3322                             ', CAST(47.500 AS Decimal(10, 3)), 8, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(380.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN023                              ', N'WFE9980                             ', CAST(56.500 AS Decimal(10, 3)), 12, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(678.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN024                              ', N'JRT9201                             ', CAST(37.500 AS Decimal(10, 3)), 10, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(375.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN025                              ', N'PLX4444                             ', CAST(54.750 AS Decimal(10, 3)), 13, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(711.750 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN026                              ', N'QWS7788                             ', CAST(59.990 AS Decimal(10, 3)), 9, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(539.910 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN027                              ', N'BHT5599                             ', CAST(30.000 AS Decimal(10, 3)), 12, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(360.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN028                              ', N'JRT9201                             ', CAST(37.500 AS Decimal(10, 3)), 6, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(225.000 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN029                              ', N'NS7503                              ', CAST(33.750 AS Decimal(10, 3)), 5, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(168.750 AS Decimal(10, 3)))
INSERT [dbo].[venta_detalle] ([ven_correl], [art_codigo], [ven_precio], [ven_cantidad], [ven_dsct], [ven_igv], [ven_subtotal]) VALUES (N'VEN030                              ', N'NSR6542                             ', CAST(50.000 AS Decimal(10, 3)), 10, CAST(0.000 AS Decimal(10, 3)), NULL, CAST(500.000 AS Decimal(10, 3)))
ALTER TABLE [dbo].[Articulo]  WITH CHECK ADD FOREIGN KEY([cat_codigo])
REFERENCES [dbo].[Categoria] ([cat_codigo])
GO
ALTER TABLE [dbo].[venta]  WITH CHECK ADD FOREIGN KEY([cli_codigo])
REFERENCES [dbo].[cliente] ([cli_codigo])
GO
ALTER TABLE [dbo].[venta]  WITH CHECK ADD FOREIGN KEY([vnd_codigo])
REFERENCES [dbo].[vendedor] ([vnd_codigo])
GO
ALTER TABLE [dbo].[venta_detalle]  WITH CHECK ADD FOREIGN KEY([art_codigo])
REFERENCES [dbo].[Articulo] ([art_codigo])
GO
ALTER TABLE [dbo].[venta_detalle]  WITH CHECK ADD FOREIGN KEY([ven_correl])
REFERENCES [dbo].[venta] ([ven_correl])
GO
USE [master]
GO
ALTER DATABASE [ENVIOS] SET  READ_WRITE 
GO
