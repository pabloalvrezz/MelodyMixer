package com.example.reproductor.API;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// clase que representa los resultados de la API
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultadosDeApi {

    public String wrapperType;
    public String kind;
    public int artistId;
    public int collectionId;
    public int trackId;
    public String artistName;
    public String collectionName;
    public String trackName;
    public String collectionCensoredName;
    public String trackCensoredName;
    public String artistViewUrl;
    public String collectionViewUrl;
    public String trackViewUrl;
    public String previewUrl;
    public String artworkUrl30;
    public String artworkUrl60;
    public String artworkUrl100;
    public double collectionPrice;
    public double trackPrice;
    public Date releaseDate;
    public String collectionExplicitness;
    public String trackExplicitness;
    public int discCount;
    public int discNumber;
    public int trackCount;
    public int trackNumber;
    public int trackTimeMillis;
    public String country;
    public String currency;
    public String primaryGenreName;
    public boolean isStreamable;
}
