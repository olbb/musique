/*
 * Copyright (c) 2008, 2009, 2010 Denis Tulskiy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * version 3 along with this work.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.tulskiy.musique.playlist.formatting;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.jaudiotagger.tag.FieldKey;
import org.junit.Before;
import org.junit.Test;

import com.tulskiy.musique.track.Track;
import com.tulskiy.musique.playlist.formatting.tokens.Expression;

/**
 * @Author: Denis Tulskiy
 * @Date: Feb 6, 2010
 */
public class ParserTest {
    Track s;

    @Before
    public void setUp() {
        s = new Track();
        File file = new File("testfiles/ogg/sample.ogg");
        s.getTrackData().setLocation(file.toURI().toString());
    }

    @Test
    public void testBrackets() {
        Expression t = Parser.parse("[%artist% - ]%title%");

        s.getTrackData().addTitle("title");
        assertEquals("title", t.eval(s));

        s.getTrackData().addArtist("artist");
        assertEquals("artist - title", t.eval(s));
    }

    @Test
    public void testIf3() {
        Expression t = Parser.parse("$if3(%artist%, %title%, %albumArtist%, unknown)");

        s.getTrackData().setTagFieldValues(FieldKey.ARTIST, "artist");
        assertEquals("artist", t.eval(s));
        s.getTrackData().removeTagField(FieldKey.ARTIST);
        s.getTrackData().setTagFieldValues(FieldKey.TITLE, "title");
        assertEquals("title", t.eval(s));
        s.getTrackData().setTagFieldValues(FieldKey.TITLE, "");
        s.getTrackData().setTagFieldValues(FieldKey.ALBUM_ARTIST, "album artist");
        assertEquals("album artist", t.eval(s));
        s.getTrackData().removeTagField(FieldKey.ALBUM_ARTIST);
        // file name is taken once title is empty
        assertEquals("sample", t.eval(s));
        
        t = Parser.parse("$if3(%genre%, unknown)");
        s.getTrackData().setTagFieldValues(FieldKey.GENRE, "genre");
        assertEquals("genre", t.eval(s));
        s.getTrackData().setTagFieldValues(FieldKey.GENRE, "");
        assertEquals("unknown", t.eval(s));
        s.getTrackData().setTagFieldValues(FieldKey.GENRE, (String) null);
        assertEquals("unknown", t.eval(s));
    }

    @Test
    public void testIf1() {
        Expression t = Parser.parse("$if1(%artist%,%artist%,%title%)");

        s.getTrackData().setTagFieldValues(FieldKey.ARTIST, "artist");
        s.getTrackData().setTagFieldValues(FieldKey.TITLE, "title");
        assertEquals("artist", t.eval(s));

        s.getTrackData().removeTagField(FieldKey.ARTIST);
        assertEquals("title", t.eval(s));
    }

    @Test
    public void testQuot() {
        Expression t = Parser.parse("'%artist%'%title%");

        s.getTrackData().addArtist("artist here");
        s.getTrackData().addTitle("title here");

        assertEquals("%artist%title here", t.eval(s));
    }

    @Test
    public void testSmth() {
        Expression t = Parser.parse("$if1($strcmp(%albumArtist%,%artist%),%artist%,$if3(%album%,Unknown))");

        s.getTrackData().addAlbumArtist("album artist");
        s.getTrackData().addYear("year");
        s.getTrackData().addAlbum("album");
        s.getTrackData().addDisc("1");
        s.getTrackData().addTrack("10");
        s.getTrackData().addArtist("artist");
        s.getTrackData().addTitle("title");

//        System.out.println(t.eval(s));
    }
}
