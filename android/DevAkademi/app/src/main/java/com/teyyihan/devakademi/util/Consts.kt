package com.teyyihan.devakademi.util

import okhttp3.MediaType

object Consts {

//    ['CORPORATE', 'INDIVIDUAL'] SELLER TYPE
    // category 0 'Emlak', 'Vasıta', 'İkinci El ve Sıfır Alışveriş']
    // category 1 ['Arazi, SUV & Pickup', 'Bilgisayar', 'Cep Telefonu', 'Ev Elektroniği', 'Konut', 'Otomobil', 'İş Yeri']
    // category 2 ['Acura', 'Akıllı Ev Sistemleri', 'Alfa Romeo', 'Aston Martin', 'Audi', 'BMW', 'Bentley', 'Cadillac', 'Chery', 'Chevrolet', 'Chrysler', 'Citroën', 'DS Automobiles', 'Dacia', 'Daewoo', 'Daihatsu', 'Devren Kiralık', 'Devren Satılık', 'Dizüstü (Notebook)', 'Dodge', 'Ev Müzik Sistemleri', 'Ev Sinema Sistemleri', 'Ferrari', 'Fiat', 'Ford', 'GMC', 'Geely', 'Güvenlik Sistemleri', 'Honda', 'Hummer', 'Hyundai', 'Infiniti', 'Isuzu', 'Jaguar', 'Jeep', 'Kia', 'Kiralık', 'Lada', 'Lamborghini', 'Lancia', 'Land Rover', 'Lexus', 'Lincoln', 'MG', 'Mahindra', 'Maserati', 'Mazda', 'Mercedes - Benz', 'Mini', 'Mitsubishi', 'Modeller', 'Nissan', 'Opel', 'Peugeot', 'Porsche', 'Poyraz', 'Proton', 'Renault', 'Rezvani', 'Rolls-Royce', 'Rover', 'Saab', 'Satılık', 'Seat', 'Skoda', 'Smart', 'SsangYong', 'Subaru', 'Suzuki', 'Tata', 'Taşınabilir Ses Sistemleri', 'Telefon', 'Televizyon', 'Tesla', 'Tofaş', 'Toplu Satış', 'Toyota', 'Uydu & Ekipmanları', 'Volkswagen', 'Volvo']
    const val PAGE_SIZE = 10
    val CONTENT_TYPE = MediaType.parse("application/json")

    private const val LOCAL_IP = "192.168.1.7"
    const val SPRING_BASE_RUL = "http://${LOCAL_IP}:8080/"
    const val DEVAKADEMI_BASE_RUL = "https://devakademi.sahibinden.com/"

    const val DATABASE_NAME = "main"

}