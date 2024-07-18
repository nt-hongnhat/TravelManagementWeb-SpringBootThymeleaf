package com.nthn.springbootthymeleaf.constants;

public final class ModelViewConstants {
	
	public static class AttributeName {
		
		public static final String ACCOUNT = "account";
		
		public static final String AGENCY = "agency";
		
		public static final String AVATAR = "avatar";
		
		public static final String BOOKING = "booking";
		
		public static final String BOOKINGS = "bookings";
		
		public static final String CATEGORIES = "categories";
		
		public static final String CATEGORY = "category";
		
		public static final String CHART_AREA_DATA = "chartAreaData";
		
		public static final String CHART_DATA = "chartData";
		
		public static final String COUNT_BOOKINGS = "countBookings";
		
		public static final String COUNT_BOOKINGS_NOT_PAID = "countBookingsNotPaid";
		
		public static final String COUNT_BOOKINGS_PAID = "countBookingsPaid";
		
		public static final String CURRENT_USER = "currentUser";
		
		public static final String CUSTOMER = "customer";
		
		public static final String CUSTOMERS = "customers";
		
		public static final String DISTRICT = "district";
		
		public static final String DISTRICTS = "districts";
		
		public static final String DURATIONS = "durations";
		
		public static final String ERROR_MESSAGE = "errorMessage";
		
		public static final String FEEDBACK = "feedback";
		
		public static final String FEEDBACKS = "feedbacks";
		
		public static final String KEY_SET = "keySet";
		
		public static final String NOW = "now";
		
		public static final String PAYMENT = "payment";
		
		public static final String PAYMENTS = "payments";
		
		public static final String PERMISSION = "permission";
		
		public static final String PERMISSIONS = "permissions";
		
		public static final String PLACES = "places";
		
		public static final String PROFILE = "profile";
		
		public static final String PROVINCES = "provinces";
		
		public static final String REVENUE_ANNUAL = "revenueAnnual";
		
		public static final String REVENUE_MONTHLY = "revenueMonthly";
		
		public static final String STATS = "stats";
		
		public static final String SUCCESS_MESSAGE = "successMessage";
		
		public static final String TIME = "time";
		
		public static final String TOTALS = "totals";
		
		public static final String TOUR = "tour";
		
		public static final Object TOURS = "tours";
		
		public static final String TOUR_GROUP = "tourGroup";
		
		public static final String TOUR_GROUPS = "tourGroups";
		
		public static final String TOUR_TICKETS = "tourTickets";
		
		public static final String USER = "user";
		
		public static final String USERS = "users";
		
		public static final String VALUES = "values";
	}
	
	public static class ViewPath {
		
		public static final String CUSTOMERS = "/customers";
		
		public static final String DASHBOARD = "views/dashboard";
		
		public static final String FOLDER_BOOKING = "views/booking";
		
		public static final String BILL = FOLDER_BOOKING + "/bill";
		
		public static final String HOME = "views/home";
		
		public static final String PROFILE = "views/profile";
		
		public static final String REDIRECT = "redirect:/";
		
		private static final String FOLDER_ADMIN = "views/admin";
		
		public static final String CUSTOMER_EDIT = FOLDER_ADMIN + "/customer/edit";
		
		public static final String CUSTOMER_LIST = FOLDER_ADMIN + "/customer/list";
		
		public static final String CUSTOMER_CREATE = FOLDER_ADMIN + "/customer/create";
	}
}
