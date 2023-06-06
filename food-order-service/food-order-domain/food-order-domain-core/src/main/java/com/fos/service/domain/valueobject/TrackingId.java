package com.fos.service.domain.valueobject;

import java.util.UUID;

import com.fos.common.domain.valueobject.BaseId;

public class TrackingId extends BaseId<UUID>{

	public TrackingId(UUID value) {
		super(value);
	}

	
}
