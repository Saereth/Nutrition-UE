package com.breakinblocks.nutritional.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

public final class HexColor {

    public static final Codec<Integer> CODEC = Codec.STRING.flatXmap(
            HexColor::decode,
            i -> DataResult.success(encode(i))
    );

    private HexColor() {}

    public static DataResult<Integer> decode(String input) {
        String hex = input.startsWith("#") ? input.substring(1) : input;
        try {
            return DataResult.success(Integer.parseUnsignedInt(hex, 16));
        } catch (NumberFormatException e) {
            return DataResult.error(() -> "Invalid hex color: " + input);
        }
    }

    public static String encode(int rgb) {
        return "#" + String.format("%06x", rgb & 0xFFFFFF);
    }
}
