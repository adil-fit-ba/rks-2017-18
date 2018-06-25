IF OBJECT_ID(N'[__EFMigrationsHistory]') IS NULL
BEGIN
    CREATE TABLE [__EFMigrationsHistory] (
        [MigrationId] nvarchar(150) NOT NULL,
        [ProductVersion] nvarchar(32) NOT NULL,
        CONSTRAINT [PK___EFMigrationsHistory] PRIMARY KEY ([MigrationId])
    );
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE TABLE [Drzava] (
        [Id] int NOT NULL IDENTITY,
        [Naziv] nvarchar(max) NULL,
        CONSTRAINT [PK_Drzava] PRIMARY KEY ([Id])
    );
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE TABLE [KorisnickiNalog] (
        [Id] int NOT NULL IDENTITY,
        [KorisnickoIme] nvarchar(max) NULL,
        [Lozinka] nvarchar(max) NULL,
        CONSTRAINT [PK_KorisnickiNalog] PRIMARY KEY ([Id])
    );
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE TABLE [Opstina] (
        [Id] int NOT NULL IDENTITY,
        [Naziv] nvarchar(max) NULL,
        [DrzavaID] int NOT NULL,
        CONSTRAINT [PK_Opstina] PRIMARY KEY ([Id]),
        CONSTRAINT [FK_Opstina_Drzava_DrzavaID] FOREIGN KEY ([DrzavaID]) REFERENCES [Drzava] ([Id]) ON DELETE CASCADE
    );
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE TABLE [AutorizacijskiToken] (
        [Id] int NOT NULL IDENTITY,
        [Vrijednost] nvarchar(max) NULL,
        [KorisnickiNalogId] int NOT NULL,
        [VrijemeEvidentiranja] datetime2 NOT NULL,
        [IpAdresa] nvarchar(max) NULL,
        CONSTRAINT [PK_AutorizacijskiToken] PRIMARY KEY ([Id]),
        CONSTRAINT [FK_AutorizacijskiToken_KorisnickiNalog_KorisnickiNalogId] FOREIGN KEY ([KorisnickiNalogId]) REFERENCES [KorisnickiNalog] ([Id]) ON DELETE CASCADE
    );
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE TABLE [Korisnik] (
        [Id] int NOT NULL IDENTITY,
        [Ime] nvarchar(max) NULL,
        [Prezime] nvarchar(max) NULL,
        [OpstinaID] int NOT NULL,
        [KorisnickiNalogId] int NULL,
        CONSTRAINT [PK_Korisnik] PRIMARY KEY ([Id]),
        CONSTRAINT [FK_Korisnik_KorisnickiNalog_KorisnickiNalogId] FOREIGN KEY ([KorisnickiNalogId]) REFERENCES [KorisnickiNalog] ([Id]) ON DELETE NO ACTION,
        CONSTRAINT [FK_Korisnik_Opstina_OpstinaID] FOREIGN KEY ([OpstinaID]) REFERENCES [Opstina] ([Id]) ON DELETE CASCADE
    );
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE TABLE [Posiljka] (
        [Id] int NOT NULL IDENTITY,
        [Masa] real NOT NULL,
        [Napomena] nvarchar(max) NULL,
        [BrojPosiljke] int NOT NULL,
        [Iznos] real NOT NULL,
        [PlacaPouzecem] bit NOT NULL,
        [KorisnikPrimaocID] int NOT NULL,
        [KorisnikEvidentiraoID] int NULL,
        [VrijemeEvidentirana] datetime2 NULL,
        CONSTRAINT [PK_Posiljka] PRIMARY KEY ([Id]),
        CONSTRAINT [FK_Posiljka_Korisnik_KorisnikEvidentiraoID] FOREIGN KEY ([KorisnikEvidentiraoID]) REFERENCES [Korisnik] ([Id]) ON DELETE NO ACTION,
        CONSTRAINT [FK_Posiljka_Korisnik_KorisnikPrimaocID] FOREIGN KEY ([KorisnikPrimaocID]) REFERENCES [Korisnik] ([Id]) ON DELETE CASCADE
    );
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE INDEX [IX_AutorizacijskiToken_KorisnickiNalogId] ON [AutorizacijskiToken] ([KorisnickiNalogId]);
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE INDEX [IX_Korisnik_KorisnickiNalogId] ON [Korisnik] ([KorisnickiNalogId]);
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE INDEX [IX_Korisnik_OpstinaID] ON [Korisnik] ([OpstinaID]);
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE INDEX [IX_Opstina_DrzavaID] ON [Opstina] ([DrzavaID]);
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE INDEX [IX_Posiljka_KorisnikEvidentiraoID] ON [Posiljka] ([KorisnikEvidentiraoID]);
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    CREATE INDEX [IX_Posiljka_KorisnikPrimaocID] ON [Posiljka] ([KorisnikPrimaocID]);
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624120747_inicijalno')
BEGIN
    INSERT INTO [__EFMigrationsHistory] ([MigrationId], [ProductVersion])
    VALUES (N'20180624120747_inicijalno', N'2.1.0-rtm-30799');
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624150751_deviceinfo')
BEGIN
    ALTER TABLE [AutorizacijskiToken] ADD [deviceInfo] nvarchar(max) NULL;
END;

GO

IF NOT EXISTS(SELECT * FROM [__EFMigrationsHistory] WHERE [MigrationId] = N'20180624150751_deviceinfo')
BEGIN
    INSERT INTO [__EFMigrationsHistory] ([MigrationId], [ProductVersion])
    VALUES (N'20180624150751_deviceinfo', N'2.1.0-rtm-30799');
END;

GO

