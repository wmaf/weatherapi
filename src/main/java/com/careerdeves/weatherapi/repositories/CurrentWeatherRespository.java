package com.careerdeves.weatherapi.repositories;


import com.careerdeves.weatherapi.models.CurrentWeatherReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentWeatherRespository extends JpaRepository<CurrentWeatherReport, Long> {
}
