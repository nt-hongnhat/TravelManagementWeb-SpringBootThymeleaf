package com.nthn.springbootthymeleaf.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public final class ValidatorConstants {
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Field {
		
		public static final String EMAIL = "email";
		
		public static final String PASSWORD = "password";
		
		public static final String FIRST_NAME = "firstName";
		
		public static final String LAST_NAME = "lastName";
		
		public static final String ADDRESS = "address";
		
		public static final String PHONE = "phone";
		
		public static final String CITY = "city";
		
		public static final String COUNTRY = "country";
		
		public static final String ZIP_CODE = "zipCode";
		
		public static final String AGENCY_NAME = "agencyName";
		
		public static final String AGENCY_ADDRESS = "agencyAddress";
		
		public static final String AGENCY_PHONE = "agencyPhone";
		
		public static final String AGENCY_EMAIL = "agencyEmail";
		
		public static final String AGENCY_WEBSITE = "agencyWebsite";
		
		public static final String CATEGORY_NAME = "categoryName";
		
		public static final String DESTINATION_NAME = "destinationName";
		
		public static final String DESTINATION_ADDRESS = "destinationAddress";
		
		public static final String DESTINATION_PHONE = "destinationPhone";
		
		public static final String DESTINATION_EMAIL = "destinationEmail";
		
		public static final String DESTINATION_WEBSITE = "destinationWebsite";
		
		public static final String DESTINATION_DESCRIPTION = "destinationDescription";
		
		public static final String DESTINATION_IMAGE = "destinationImage";
		
		public static final String DESTINATION_LATITUDE = "destinationLatitude";
		
		public static final String DESTINATION_LONGITUDE = "destinationLongitude";
		
		public static final String DESTINATION_CATEGORY = "destinationCategory";
		
		public static class Tour {
			
			public static final String NAME = "tourName";
			
			public static final String DESCRIPTION = "tourDescription";
			
			public static final String IMAGE = "tourImage";
			
			public static final String START_DATE = "tourStartDate";
			
			public static final String END_DATE = "tourEndDate";
			
			public static final String PRICE = "tourPrice";
			
			public static final String CATEGORY = "tourCategory";
			
			public static final String DESTINATION = "tourDestination";
			
			public static final String AGENCY = "tourAgency";
			
			public static final String TOUR_TICKET = "tourTicket";
			
			public static final String TOUR_TICKET_NAME = "tourTicketName";
			
			public static final String TOUR_TICKET_PRICE = "tourTicketPrice";
			
			public static final String TOUR_TICKET_DESCRIPTION = "tourTicketDescription";
			
			public static final String TOUR_TICKET_IMAGE = "tourTicketImage";
			
			public static final String TOUR_TICKET_QUANTITY = "tourTicketQuantity";
			
			public static final String TOUR_TICKET_GROUP = "tourTicketGroup";
			
			public static final String TOUR_TICKET_MAX_SLOT = "tourTicketMaxSlot";
			
			public static final String UNIT_PRICE = "unitPrice";
			
			public static final String DURATION = "duration";
			
			public static final String ITINERARY = "itinerary";
		}
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Table {
		
		public static final String TOUR = "tour_info";
		
		public static final String TOUR_TICKET = "tour_ticket";
		
		public static final String TOUR_SCHEDULE = "tour_schedule";
		
		public static final String USER = "user";
		
		public static final String AGENCY = "agency";
		
		public static final String CATEGORY = "category";
		
		public static final String DESTINATION = "destination";
		
		public static final String TOUR_DESTINATION = "tour_destination";
		
		public static final String TOUR_AGENCY = "tour_agency";
		
		public static final String TOUR_CATEGORY = "tour_category";
		
		public static final String TOUR_DESTINATION_CATEGORY = "tour_destination_category";
		
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class ErrorCode {
		
		public static final String UNIQUE = "unique";
		
		public static final String NOT_NULL = "not_null";
		
		public static final String NOT_BLANK = "not_blank";
		
		public static final String NOT_EMPTY = "not_empty";
		
		public static final String NOT_VALID = "not_valid";
		
		public static final String NOT_VALID_EMAIL = "not_valid_email";
	}
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class DefaultMessage {
		
		public static final String EXIST = "%s is exists";
		
		public static final String NULL = "%s is null";
		
	}
}
