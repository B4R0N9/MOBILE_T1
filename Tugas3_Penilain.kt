// 1. Deklarasi Data Class
data class NilaiMahasiswa(
    val nim: String,
    val nama: String,
    val mataKuliah: String,
    val nilai: Int
)

// Fungsi pembantu untuk konversi nilai ke grade
fun getGrade(nilai: Int): String {
    return when (nilai) {
        in 85..100 -> "A"
        in 70..84 -> "B"
        in 60..69 -> "C"
        in 50..59 -> "D"
        else -> "E"
    }
}

fun main() {
    // 2. Buat list berisi minimal 10 data mahasiswa
    val daftarMahasiswa = listOf(
        NilaiMahasiswa("2024001", "Budi Santoso", "Pemrograman", 85),
        NilaiMahasiswa("2024002", "Ani Wijaya", "Pemrograman", 92),
        NilaiMahasiswa("2024003", "Citra Dewi", "Pemrograman", 68),
        NilaiMahasiswa("2024004", "Dani Pratama", "Pemrograman", 45),
        NilaiMahasiswa("2024005", "Eka Putri", "Pemrograman", 78),
        NilaiMahasiswa("2024006", "Fajar Ramadhan", "Pemrograman", 55),
        NilaiMahasiswa("2024007", "Gita Permata", "Pemrograman", 88),
        NilaiMahasiswa("2024008", "Hadi Saputra", "Pemrograman", 62),
        NilaiMahasiswa("2024009", "Indah Lestari", "Pemrograman", 70),
        NilaiMahasiswa("2024010", "Joko Susilo", "Pemrograman", 40)
    )

    // --- OPERASI COLLECTION ---

    // 1. Tampilkan semua data mahasiswa
    println("----- DATA NILAI MAHASISWA -----")
    println("No\tNIM\t\tNama\t\tMataKuliah\tNilai")
    daftarMahasiswa.forEachIndexed { index, mhs ->
        println("${index + 1}\t${mhs.nim}\t${mhs.nama.padEnd(15)}\t${mhs.mataKuliah}\t${mhs.nilai}")
    }

    // 4, 5, 6. Statistik (Rata-rata, Tertinggi, Terendah)
    val rataRata = daftarMahasiswa.map { it.nilai }.average()
    val tertinggi = daftarMahasiswa.maxByOrNull { it.nilai }
    val terendah = daftarMahasiswa.minByOrNull { it.nilai }

    println("\n----- STATISTIK -----")
    println("Total Mahasiswa : ${daftarMahasiswa.size}")
    println("Rata-rata Nilai : $rataRata")
    println("Nilai Tertinggi : ${tertinggi?.nilai} (${tertinggi?.nama})")
    println("Nilai Terendah  : ${terendah?.nilai} (${terendah?.nama})")

    // 2. Filter mahasiswa yang lulus (nilai >= 70)
    println("\n----- MAHASISWA LULUS -----")
    val lulus = daftarMahasiswa.filter { it.nilai >= 70 }
    lulus.forEachIndexed { i, mhs -> println("${i + 1}. ${mhs.nama} - ${mhs.nilai} (${getGrade(mhs.nilai)})") }

    // 3. Filter mahasiswa tidak lulus (nilai < 70)
    println("\n----- MAHASISWA TIDAK LULUS -----")
    daftarMahasiswa.filter { it.nilai < 70 }.forEach { println("- ${it.nama} (${it.nilai})") }

    // 7. Urutkan berdasarkan nilai (Descending)
    println("\n----- URUTAN NILAI (TERTINGGI KE TERENDAH) -----")
    val urutDesc = daftarMahasiswa.sortedByDescending { it.nilai }
    urutDesc.take(3).forEach { println("${it.nama}: ${it.nilai}") } // Tampilkan 3 besar saja

    // 8 & 9. Kelompokkan dan Hitung jumlah per grade
    println("\n----- JUMLAH PER GRADE -----")
    val perGrade = daftarMahasiswa.groupBy { getGrade(it.nilai) }
    // Menampilkan jumlah per grade secara urut A-E
    listOf("A", "B", "C", "D", "E").forEach { g ->
        val jumlah = perGrade[g]?.size ?: 0
        println("Grade $g: $jumlah mahasiswa")
    }

    // 10. Cari mahasiswa berdasarkan nama (contains "Ani")
    val cariNama = "Ani"
    println("\n----- PENCARIAN NAMA: '$cariNama' -----")
    val hasilCari = daftarMahasiswa.filter { it.nama.contains(cariNama, ignoreCase = true) }
    hasilCari.forEach { println("Ditemukan: ${it.nama} (NIM: ${it.nim})") }
}