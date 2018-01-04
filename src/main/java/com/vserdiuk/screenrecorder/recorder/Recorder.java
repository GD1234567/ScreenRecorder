package com.vserdiuk.screenrecorder.recorder;

import org.apache.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.nio.ByteOrder;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.AudioFormatKeys.MIME_QUICKTIME;
import static org.monte.media.AudioFormatKeys.MediaType;
import static org.monte.media.AudioFormatKeys.MimeTypeKey;
import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.VideoFormatKeys.*;

/**
 * Created by vserdiuk on 1/4/18.
 */

public class Recorder {

    final static Logger logger = Logger.getLogger(Recorder.class);

    private ScreenRecorder screenRecorder;

    public void startRecording(String outputFolder, GraphicsConfiguration graphicsConfiguration) throws Exception {
        File fileFolder = new File(outputFolder);
        this.screenRecorder = new ScreenRecorder(graphicsConfiguration, null,
                new Format(MediaTypeKey, FormatKeys.MediaType.FILE,
                        MimeTypeKey, MIME_QUICKTIME),
                new Format(MediaTypeKey, FormatKeys.MediaType.VIDEO,
                        EncodingKey, ENCODING_QUICKTIME_ANIMATION,
                        CompressorNameKey, COMPRESSOR_NAME_QUICKTIME_ANIMATION,
                        DepthKey, 24, FrameRateKey, new Rational(15, 1)),
                new Format(MediaTypeKey, MediaType.VIDEO,
                        EncodingKey, "white",
                        FrameRateKey, new Rational(30, 1)),
                new Format(MediaTypeKey, MediaType.AUDIO,
                        EncodingKey, ENCODING_QUICKTIME_TWOS_PCM,
                        FrameRateKey, new Rational(48000, 1),
                        SampleSizeInBitsKey, 16,
                        ChannelsKey, 1, SampleRateKey, new Rational(48000, 1),
                        SignedKey, true, ByteOrderKey, ByteOrder.BIG_ENDIAN), fileFolder);
        this.screenRecorder.start();
        logger.info("Screen recording waw started");
    }

    public void stopRecording() throws Exception {
        this.screenRecorder.stop();
        logger.info("Screen recording was stopped");
    }

} 