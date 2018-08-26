package com.dropical.client.world;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FishRecorder {
    private String aniName;
    private List<List<String>> keyframeList = new ArrayList<List<String>>();

    public FishRecorder(String aniName) {
        this.aniName = aniName;
    }

    public void addKeyframe(float x, float y) {
        if(keyframeList.size() <= 0) {  //if no texture is available, the first keyframe gets the default fish texture
            addKeyframe(x, y, "fish0.png");
        }
        else {
            addKeyframe(x, y, "");
        }
    }
    public void addKeyframe(float x, float y, String texturePath) {
        //create new line inside the ArrayList for the next keyframes
        keyframeList.add(new ArrayList<String>());

        //add position as a keyframe into the row
//        keyframeList.get(keyframeList.size()-1).add("{transform: translate(" + x + "px, " + new BigDecimal(720).subtract(new BigDecimal(y)) + "px)}\n");
        BigDecimal xVW = (new BigDecimal("100").divide(new BigDecimal(1280), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(x))).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal yFlip = new BigDecimal(new BigDecimal(720).subtract(new BigDecimal(y)).toString());
        BigDecimal yVH = (new BigDecimal("100").divide(new BigDecimal(720), 2, BigDecimal.ROUND_HALF_UP).multiply(yFlip)).setScale(2, BigDecimal.ROUND_HALF_UP);
        keyframeList.get(keyframeList.size()-1).add("{transform: translate(" + xVW + "vw, " + yVH + "vh)}\n");

        //add texture as a keyfram into the row
        keyframeList.get(keyframeList.size()-1).add("{background-image: url(\"" + texturePath + "\")}\n");
    }

    public String buildString() {
        int keyframeAmount = keyframeList.size();
        BigDecimal keyframePercent; //0.00% {do something with CSS}
        String currentKeyframePosition;
        String currentKeyframeTexture;
        String lastKeyframePositions = "";
        String lastKeyframeTexture = "";

        //------------ Build CSS Code ------------//
        String code = "@keyframes " + this.aniName + " {\n";

        for(int i = 0; i < keyframeList.size(); i++) {
            //calculates the percentage
            keyframePercent = new BigDecimal("100").divide(new BigDecimal(keyframeAmount), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(i));

            //make sure to stop if 100% is reached
            if(keyframePercent.compareTo(new BigDecimal("100")) < 0) {
                //get keyframe values
                currentKeyframePosition = keyframeList.get(i).get(0);
                currentKeyframeTexture = keyframeList.get(i).get(1);

                if(!currentKeyframePosition.equals(lastKeyframePositions)) {    //makes sure to skip positions, that always stay the same
                    lastKeyframePositions = currentKeyframePosition;

                    if(i%8==0) {
                        code += "    " + keyframePercent + "% " + currentKeyframePosition;
                    }
                }
                if(!currentKeyframeTexture.equals(lastKeyframeTexture)) {   //makes sure to skip textures, that always stay the same
                    if(!lastKeyframeTexture.equals("")) {
                        code += "    " + new BigDecimal(keyframePercent.toString()).subtract(new BigDecimal("0.01")) + "% " + lastKeyframeTexture;
                    }
                    lastKeyframeTexture = currentKeyframeTexture;

                    code += "    " + keyframePercent + "% " + currentKeyframeTexture;
                }
            }
        }

        code += "    " + new BigDecimal("100") + "% " + lastKeyframePositions;
        code += "    " + new BigDecimal("100") + "% " + lastKeyframeTexture;
        code += "}";
        //------------

        return code;
    }

    public void buildCSS() {
        PrintWriter pw = null;
        try {
            File file = new File(aniName + ".css");
            for(int i = 1; file.exists() && !file.isDirectory(); i++) {
                file = new File(aniName + i + ".css");
            }
            pw = new PrintWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.write(buildString());
        pw.close();
    }

}
