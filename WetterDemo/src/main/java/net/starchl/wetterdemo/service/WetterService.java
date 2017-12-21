package net.starchl.wetterdemo.service;

import java.util.List;

public interface WetterService {
	public List<String> getStadt(String land);

	public String getWetterAsString(String stadt, String land);
}
