/*
 * Copyright (c) 2008, 2009, 2010, 2011 Denis Tulskiy
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

package com.tulskiy.musique.plugins.discogs.model;

import com.tulskiy.musique.track.Track;

/**
 * @author mliauchuk
 */
public class MusiqueTrackListModel extends DiscogsDefaultListModel {

	public Track getEx(int index) {
		Object item = super.get(index);
		if (item instanceof Track) {
			return (Track) item;
		}
		
		return null;
	}

	@Override
	public Object get(int index) {
		Track track = getEx(index);
		return track == null ? null : getTrackDescription(track);
	}

	private static String getTrackDescription(Track track) {
        StringBuilder sb = new StringBuilder();
        sb.append(track.getTrackData().getFileName())
                .append(" (")
                .append(track.getTrackData().getLength())
                .append(')');
        return sb.toString();
	}

}
