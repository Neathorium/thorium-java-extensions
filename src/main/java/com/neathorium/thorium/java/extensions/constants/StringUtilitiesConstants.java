package com.neathorium.thorium.java.extensions.constants;

import com.neathorium.thorium.java.extensions.namespaces.utilities.SetExtensions;

import java.util.Set;

public abstract class StringUtilitiesConstants {
    public static final Character BRAILLE_PATTERN_BLANK = '⠀';
    public static final Character HANGUL_FILLER = 'ㅤ';
    public static final Character KHMER_VOWEL_INHERET_AQ = '឴';
    public static final Character KHMER_VOWEL_INHERET_AA = '឵';
    public static final Character HANGUL_CHOSEONG_FILLER = 'ᅟ';
    public static final Character HANGUL_JUNGSEONG_FILLER = 'ᅠ';
    public static final Character MONGOLIAN_FREE_VARIATION_SELECTOR_ONE = '᠋';
    public static final Character COMBINING_GRAPHEME_JOINER = '͏';


    public static final Set<Character> INVISIBLE_BLANKS_SET = SetExtensions.ofWithHashMap(
        StringUtilitiesConstants.BRAILLE_PATTERN_BLANK,
        StringUtilitiesConstants.HANGUL_FILLER,
        StringUtilitiesConstants.KHMER_VOWEL_INHERET_AQ,
        StringUtilitiesConstants.KHMER_VOWEL_INHERET_AA,
        StringUtilitiesConstants.HANGUL_CHOSEONG_FILLER,
        StringUtilitiesConstants.HANGUL_JUNGSEONG_FILLER,
        StringUtilitiesConstants.MONGOLIAN_FREE_VARIATION_SELECTOR_ONE,
        StringUtilitiesConstants.COMBINING_GRAPHEME_JOINER
    );
}
