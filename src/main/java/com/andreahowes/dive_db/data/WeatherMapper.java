package com.andreahowes.dive_db.data;

import com.andreahowes.dive_db.logic.Calculations;
import com.andreahowes.dive_db.logic.Weather;
import org.springframework.stereotype.Service;

@Service
public class WeatherMapper {


    public static Weather mapWeather(ApiWeather apiWeather) {

        Weather weather = new Weather();

        Long sunrise = apiWeather.getSys().getSunrise();
        weather.setSunrise(Calculations.getTheSunriseTime(sunrise));


        Long sunset = apiWeather.getSys().getSunset();
        weather.setSunset(Calculations.getTheSunsetTime(sunset));


        Double temp = apiWeather.getMain().getTemp();
        weather.setTemp(Calculations.getTheTempInF(temp));


        Double maxTemp = apiWeather.getMain().getTemp_max();
        weather.setMaxTemp(Calculations.getTheTempInF(maxTemp));


        Double minTemp = apiWeather.getMain().getTemp_min();
        weather.setMinTemp(Calculations.getTheTempInF(minTemp));

        return weather;

    }


}