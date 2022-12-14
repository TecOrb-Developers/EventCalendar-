package com.bookappointmentcalender.model;

import java.util.List;

public class ProfileModel {
    private int code;
    private String message;
    private ResultBean result;
    private int referAmount;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getReferAmount() {
        return referAmount;
    }

    public void setReferAmount(int referAmount) {
        this.referAmount = referAmount;
    }

    public static class ResultBean {
        private LocBean loc;
        private String name;
        private String mobileNo;
        private String countryCode;
        private String openTime;
        private String closeTime;
        private String gender;
        private String email;
        private String ownerName;
        private boolean mobileVerify;
        private boolean emailVerify;
        private String profileImg;
        private String stripeAccountId;
        private String refId;
        private boolean referAmountStatus;
        private String stripeId;
        private String address;
        private String language;
        private String registrationNo;
        private String socialId;
        private String socialType;
        private boolean referPay;
        private String role;
        private boolean subBuy;
        private Object subName;
        private double avgRating;
        private boolean status;
        private boolean isDelete;
        private String _id;
        private String referCode;
        private String createdAt;
        private int __v;
        private Object dob;
        private TimingBean timing;
        private List<String> categoryId;

        public LocBean getLoc() {
            return loc;
        }

        public void setLoc(LocBean loc) {
            this.loc = loc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public String getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(String closeTime) {
            this.closeTime = closeTime;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public boolean isMobileVerify() {
            return mobileVerify;
        }

        public void setMobileVerify(boolean mobileVerify) {
            this.mobileVerify = mobileVerify;
        }

        public boolean isEmailVerify() {
            return emailVerify;
        }

        public void setEmailVerify(boolean emailVerify) {
            this.emailVerify = emailVerify;
        }

        public String getProfileImg() {
            return profileImg;
        }

        public void setProfileImg(String profileImg) {
            this.profileImg = profileImg;
        }

        public String getStripeAccountId() {
            return stripeAccountId;
        }

        public void setStripeAccountId(String stripeAccountId) {
            this.stripeAccountId = stripeAccountId;
        }

        public String getRefId() {
            return refId;
        }

        public void setRefId(String refId) {
            this.refId = refId;
        }

        public boolean isReferAmountStatus() {
            return referAmountStatus;
        }

        public void setReferAmountStatus(boolean referAmountStatus) {
            this.referAmountStatus = referAmountStatus;
        }

        public String getStripeId() {
            return stripeId;
        }

        public void setStripeId(String stripeId) {
            this.stripeId = stripeId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getRegistrationNo() {
            return registrationNo;
        }

        public void setRegistrationNo(String registrationNo) {
            this.registrationNo = registrationNo;
        }

        public String getSocialId() {
            return socialId;
        }

        public void setSocialId(String socialId) {
            this.socialId = socialId;
        }

        public String getSocialType() {
            return socialType;
        }

        public void setSocialType(String socialType) {
            this.socialType = socialType;
        }

        public boolean isReferPay() {
            return referPay;
        }

        public void setReferPay(boolean referPay) {
            this.referPay = referPay;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public boolean isSubBuy() {
            return subBuy;
        }

        public void setSubBuy(boolean subBuy) {
            this.subBuy = subBuy;
        }

        public Object getSubName() {
            return subName;
        }

        public void setSubName(Object subName) {
            this.subName = subName;
        }

        public double getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(double avgRating) {
            this.avgRating = avgRating;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public boolean isIsDelete() {
            return isDelete;
        }

        public void setIsDelete(boolean isDelete) {
            this.isDelete = isDelete;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getReferCode() {
            return referCode;
        }

        public void setReferCode(String referCode) {
            this.referCode = referCode;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public Object getDob() {
            return dob;
        }

        public void setDob(Object dob) {
            this.dob = dob;
        }

        public TimingBean getTiming() {
            return timing;
        }

        public void setTiming(TimingBean timing) {
            this.timing = timing;
        }

        public List<String> getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(List<String> categoryId) {
            this.categoryId = categoryId;
        }

        public static class LocBean {
            private String type;
            private List<Double> coordinates;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<Double> getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(List<Double> coordinates) {
                this.coordinates = coordinates;
            }
        }

        public static class TimingBean {

            private MondayBean Monday;

            private TuesdayBean Tuesday;

            private WednesdayBean Wednesday;

            private ThursdayBean Thursday;

            private FridayBean Friday;

            private SaturdayBean Saturday;

            private SundayBean Sunday;
            private String _id;
            private String salonId;
            private int __v;

            public MondayBean getMonday() {
                return Monday;
            }

            public void setMonday(MondayBean Monday) {
                this.Monday = Monday;
            }

            public TuesdayBean getTuesday() {
                return Tuesday;
            }

            public void setTuesday(TuesdayBean Tuesday) {
                this.Tuesday = Tuesday;
            }

            public WednesdayBean getWednesday() {
                return Wednesday;
            }

            public void setWednesday(WednesdayBean Wednesday) {
                this.Wednesday = Wednesday;
            }

            public ThursdayBean getThursday() {
                return Thursday;
            }

            public void setThursday(ThursdayBean Thursday) {
                this.Thursday = Thursday;
            }

            public FridayBean getFriday() {
                return Friday;
            }

            public void setFriday(FridayBean Friday) {
                this.Friday = Friday;
            }

            public SaturdayBean getSaturday() {
                return Saturday;
            }

            public void setSaturday(SaturdayBean Saturday) {
                this.Saturday = Saturday;
            }

            public SundayBean getSunday() {
                return Sunday;
            }

            public void setSunday(SundayBean Sunday) {
                this.Sunday = Sunday;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getSalonId() {
                return salonId;
            }

            public void setSalonId(String salonId) {
                this.salonId = salonId;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public static class MondayBean {
                private String openTiming;
                private String closeTiming;

                public String getOpenTiming() {
                    return openTiming;
                }

                public void setOpenTiming(String openTiming) {
                    this.openTiming = openTiming;
                }

                public String getCloseTiming() {
                    return closeTiming;
                }

                public void setCloseTiming(String closeTiming) {
                    this.closeTiming = closeTiming;
                }
            }

            public static class TuesdayBean {
                private String openTiming;
                private String closeTiming;

                public String getOpenTiming() {
                    return openTiming;
                }

                public void setOpenTiming(String openTiming) {
                    this.openTiming = openTiming;
                }

                public String getCloseTiming() {
                    return closeTiming;
                }

                public void setCloseTiming(String closeTiming) {
                    this.closeTiming = closeTiming;
                }
            }

            public static class WednesdayBean {
                private String openTiming;
                private String closeTiming;

                public String getOpenTiming() {
                    return openTiming;
                }

                public void setOpenTiming(String openTiming) {
                    this.openTiming = openTiming;
                }

                public String getCloseTiming() {
                    return closeTiming;
                }

                public void setCloseTiming(String closeTiming) {
                    this.closeTiming = closeTiming;
                }
            }

            public static class ThursdayBean {
                private String openTiming;
                private String closeTiming;

                public String getOpenTiming() {
                    return openTiming;
                }

                public void setOpenTiming(String openTiming) {
                    this.openTiming = openTiming;
                }

                public String getCloseTiming() {
                    return closeTiming;
                }

                public void setCloseTiming(String closeTiming) {
                    this.closeTiming = closeTiming;
                }
            }

            public static class FridayBean {
                private String openTiming;
                private String closeTiming;

                public String getOpenTiming() {
                    return openTiming;
                }

                public void setOpenTiming(String openTiming) {
                    this.openTiming = openTiming;
                }

                public String getCloseTiming() {
                    return closeTiming;
                }

                public void setCloseTiming(String closeTiming) {
                    this.closeTiming = closeTiming;
                }
            }

            public static class SaturdayBean {
                private String openTiming;
                private String closeTiming;

                public String getOpenTiming() {
                    return openTiming;
                }

                public void setOpenTiming(String openTiming) {
                    this.openTiming = openTiming;
                }

                public String getCloseTiming() {
                    return closeTiming;
                }

                public void setCloseTiming(String closeTiming) {
                    this.closeTiming = closeTiming;
                }
            }

            public static class SundayBean {
                private String openTiming;
                private String closeTiming;

                public String getOpenTiming() {
                    return openTiming;
                }

                public void setOpenTiming(String openTiming) {
                    this.openTiming = openTiming;
                }

                public String getCloseTiming() {
                    return closeTiming;
                }

                public void setCloseTiming(String closeTiming) {
                    this.closeTiming = closeTiming;
                }
            }
        }
    }
}
