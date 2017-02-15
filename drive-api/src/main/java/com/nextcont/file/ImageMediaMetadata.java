package com.nextcont.file;

import lombok.Builder;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/9
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
public class ImageMediaMetadata {

    private Integer width;

    private Integer height;

    private Integer rotation;

    private Location location;

    private String time;

    private String camreaMake;

    private String cameraModel;

    private Float aperture;

    private boolean flashUsed;

    private Float focalLength;

    private Integer isoSpeed;

    private String meteringMode;

    private String sensor;

    private String exposureMode;

    private String colorSpace;

    private String whiteBalance;

    private Float exposureBias;

    private Float maxApertureValue;

    private Integer subjectDistance;

    private String lens;
}
