package com.breakinblocks.nutrition.effects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.potion.Potion;

import com.breakinblocks.nutrition.nutrients.Nutrient;

// This class represents cleaned up and parsed potion effects
public class Effect {

    public String name;
    public Potion potion;
    public int amplifier;
    public int minimum;
    public int maximum;
    public String detect;
    public List<Nutrient> nutrients = new ArrayList<>();
    public int cumulativeModifier;
    public Enum<ParticleVisibility> particles;

    public enum ParticleVisibility {
        OPAQUE,
        TRANSLUCENT,
        TRANSPARENT
    }
}
