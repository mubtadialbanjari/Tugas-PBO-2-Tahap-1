-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 10 Nov 2025 pada 14.26
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_sosial`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `donasi`
--

CREATE TABLE `donasi` (
  `ID_Donasi` varchar(20) NOT NULL,
  `Donasi_uang` varchar(50) DEFAULT NULL,
  `Donasi_Barang` varchar(255) DEFAULT NULL,
  `Donasi_Sembako` varchar(255) DEFAULT NULL,
  `NIP` varchar(20) DEFAULT NULL,
  `User_id` varchar(20) DEFAULT NULL,
  `No_Izin` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `galangdana`
--

CREATE TABLE `galangdana` (
  `ID_GalangDana` varchar(20) NOT NULL,
  `Detail` varchar(255) DEFAULT NULL,
  `Update_Info` varchar(255) DEFAULT NULL,
  `Fundraiser` varchar(100) DEFAULT NULL,
  `NIP` varchar(20) DEFAULT NULL,
  `User_id` varchar(20) DEFAULT NULL,
  `No_Izin` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `galeri`
--

CREATE TABLE `galeri` (
  `Id_galeri` varchar(20) NOT NULL,
  `Foto` varchar(255) DEFAULT NULL,
  `Keterangan` varchar(255) DEFAULT NULL,
  `No_Izin` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `lsm`
--

CREATE TABLE `lsm` (
  `No_Izin` varchar(20) NOT NULL,
  `Nama_LSM` varchar(100) DEFAULT NULL,
  `Alamat` varchar(255) DEFAULT NULL,
  `User_IdL` varchar(50) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `donasi`
--
ALTER TABLE `donasi`
  ADD PRIMARY KEY (`ID_Donasi`);

--
-- Indeks untuk tabel `galangdana`
--
ALTER TABLE `galangdana`
  ADD PRIMARY KEY (`ID_GalangDana`);

--
-- Indeks untuk tabel `galeri`
--
ALTER TABLE `galeri`
  ADD PRIMARY KEY (`Id_galeri`);

--
-- Indeks untuk tabel `lsm`
--
ALTER TABLE `lsm`
  ADD PRIMARY KEY (`No_Izin`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
