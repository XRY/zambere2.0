package com.xrystalgenius.quartz;

import android.provider.BaseColumns;

public final class FeedReaderHelper {
	public FeedReaderHelper() {
	}

	public static abstract class GoodsInDocketTable implements BaseColumns {
		public static final String TABLE_NAME = "goodsindocket";
		public static final String COLUMN_NAME_TABLE_Id = "goodid";
		public static final String COLUMN_NAME_SUPPLIER_ID = "supplierid";
		public static final String COLUMN_NAME_ITEM_ID = "itemid";
		public static final String COLUMN_NAME_QUATITY_REQUESTED = "quantity";
		public static final String COLUMN_NAME_DELIVERY_NOTE_NUMBER = "deliverynotenumber";
		public static final String COLUMN_NAME_EXPIRY_DATE = "expirydate";

	}

	public static abstract class InternalRequisitionTable implements
			BaseColumns {
		public static final String TABLE_NAME = "internalrequisition";
		public static final String COLUMN_NAME_TABLE_Id = "irn";
		public static final String COLUMN_NAME_DATE = "supplierid";
		public static final String COLUMN_NAME_DEPT_ID = "deptid";
		public static final String COLUMN_NAME_REASON = "reason";
		public static final String COLUMN_NAME_REQUESTER = "requester";
		public static final String COLUMN_NAME_VERIFY = "verify";

	}

	public static abstract class InternalRequisitionDetailsTable implements
			BaseColumns {
		public static final String TABLE_NAME = "internalrequisitiondetails";
		public static final String COLUMN_NAME_TABLE_Id = "id";
		public static final String COLUMN_NAME_IRN = "irn";
		public static final String COLUMN_NAME_ITEM_ID = "itemid";
		public static final String COLUMN_NAME_QUANTITY_REQUESTED = "qtyrequested";
		public static final String COLUMN_NAME_QUANTITY_ISSUED = "qtyissued";
		public static final String COLUMN_NAME_DATE_ISSUED = "dateissued";

	}

	public static abstract class ReturnsTable implements BaseColumns {
		public static final String TABLE_NAME = "returns";
		public static final String COLUMN_NAME_TABLE_Id = "returnsid";
		public static final String COLUMN_NAME_RETURNED_BY = "returnedby";
		public static final String COLUMN_NAME_ITEM_ID = "itemid";
		public static final String COLUMN_NAME_QUANTITY = "qtyreturned";
		public static final String COLUMN_NAME_DATE = "date";

	}

	public static abstract class SupplierTable implements BaseColumns {
		public static final String TABLE_NAME = "supplier";
		public static final String COLUMN_NAME_TABLE_Id = "supplierid";
		public static final String COLUMN_NAME_SUPPLIER_NAME = "suppliername";
		public static final String COLUMN_NAME_SUPPLIER_LOCATION = "supplierlocation";
		public static final String COLUMN_NAME_PHONE_CONTACT = "phonecontact";
		public static final String COLUMN_NAME_CONTACT_PERSON = "contactperson";

	}

	public static abstract class DepartmentTable implements BaseColumns {
		public static final String TABLE_NAME = "department";
		public static final String COLUMN_NAME_TABLE_Id = "deptid";
		public static final String COLUMN_NAME_DEPT_NAME = "deptname";

	}

	public static abstract class ItemTable implements BaseColumns {
		public static final String TABLE_NAME = "item";
		public static final String COLUMN_NAME_TABLE_Id = "itemid";
		public static final String COLUMN_NAME_ITEM_NAME = "itemname";
		public static final String COLUMN_NAME_QUANTITY_TYPE = "quantitytype";
		public static final String COLUMN_NAME_DEPT_ID = "deptid";

	}
	
	public static abstract class StockCardTable implements BaseColumns {
		public static final String TABLE_NAME = "stockcard";
		public static final String COLUMN_NAME_TABLE_ID = "stcid";
		public static final String COLUMN_NAME_ITEM_ID = "itemid";
		public static final String COLUMN_NAME_QUANTITY = "type";
		public static final String COLUMN_NAME_TYPE = "quantity";
		public static final String COLUMN_NAME_DATE = "date";
		public static final String COLUMN_NAME_RUNNING_TOTAL = "runningtotal";
		public static final String COLUMN_NAME_SUPPLIER_ID = "supplierid";
		public static final String COLUMN_NAME_REQUESTER = "requester";
		
	}
}
