package com.xrystalgenius.quartz;

import android.provider.BaseColumns;

public final class FeedReaderHelper {
	public FeedReaderHelper(){} 
	
	public static abstract class GoodsInDocketTable implements BaseColumns {
		public static final String TABLE_NAME = "goodsindocket";
		
	}
	
	public static abstract class InternalRequisitionTable implements BaseColumns {
		public static final String TABLE_NAME = "internalrequisition";
	}
	
	public static abstract class InternalRequisitionDetailsTable implements BaseColumns {
		public static final String TABLE_NAME = "internalrequisitiondetails";
	}
	
	public static abstract class ReturnsTable implements BaseColumns {
		public static final String TABLE_NAME = "returns";
	}
}
