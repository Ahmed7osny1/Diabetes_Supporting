package com.example.supportingyourdiabetes.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import java.util.List;

public class FoodData {
    private String text;
    private List<Parsed> parsed;
    private List<Hint> hints;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Parsed> getParsed() {
        return parsed;
    }

    public void setParsed(List<Parsed> parsed) {
        this.parsed = parsed;
    }

    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }

    public static class Parsed {
        // Add fields as needed
    }

    public static class Hint {
        private Food food;
        private List<Measure> measures;

        public Food getFood() {
            return food;
        }

        public void setFood(Food food) {
            this.food = food;
        }

        public List<Measure> getMeasures() {
            return measures;
        }

        public void setMeasures(List<Measure> measures) {
            this.measures = measures;
        }

        public static class Food {
            private String foodId;
            private String label;
            private String knownAs;
            private Nutrients nutrients;
            private String category;
            private String categoryLabel;
            private String image;

            public String getFoodId() {
                return foodId;
            }

            public void setFoodId(String foodId) {
                this.foodId = foodId;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getKnownAs() {
                return knownAs;
            }

            public void setKnownAs(String knownAs) {
                this.knownAs = knownAs;
            }

            public Nutrients getNutrients() {
                return nutrients;
            }

            public void setNutrients(Nutrients nutrients) {
                this.nutrients = nutrients;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getCategoryLabel() {
                return categoryLabel;
            }

            public void setCategoryLabel(String categoryLabel) {
                this.categoryLabel = categoryLabel;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public static class Nutrients {
                private double ENERC_KCAL;
                private double PROCNT;
                private double FAT;
                private double CHOCDF;
                private double FIBTG;

                public double getENERC_KCAL() {
                    return ENERC_KCAL;
                }

                public void setENERC_KCAL(double ENERC_KCAL) {
                    this.ENERC_KCAL = ENERC_KCAL;
                }

                public double getPROCNT() {
                    return PROCNT;
                }

                public void setPROCNT(double PROCNT) {
                    this.PROCNT = PROCNT;
                }

                public double getFAT() {
                    return FAT;
                }

                public void setFAT(double FAT) {
                    this.FAT = FAT;
                }

                public double getCHOCDF() {
                    return CHOCDF;
                }

                public void setCHOCDF(double CHOCDF) {
                    this.CHOCDF = CHOCDF;
                }

                public double getFIBTG() {
                    return FIBTG;
                }

                public void setFIBTG(double FIBTG) {
                    this.FIBTG = FIBTG;
                }
            }
        }

        public static class Measure {
            private String uri;
            private String label;
            private double weight;

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }
        }
    }
}