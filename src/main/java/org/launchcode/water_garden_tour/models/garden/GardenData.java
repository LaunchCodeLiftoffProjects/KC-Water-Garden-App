package org.launchcode.water_garden_tour.models.garden;

import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

public class GardenData {

    public static List<Garden> gardenSearchFeaturesFiltered(String searchValueRaw, Integer[] featureIds, Iterable<Feature> allFeatures, List<Garden> allGardens) {
        String searchValue = searchValueRaw.toLowerCase();
        List<Garden> results = new ArrayList<>();
        Feature curFeature;

        for (Garden garden : allGardens) {
            boolean hasAllFeatures = true;

            for (Integer featureId : featureIds) {
                curFeature = getFeatureFromId(featureId, allFeatures);

                if (!garden.getFeatures().contains(curFeature)) {
                    hasAllFeatures = false;
                }
            }
            if (hasAllFeatures) {
                results.add(garden);
            }
        }

        if (!searchValue.equals("all") || !searchValue.equals("")){
            List<Garden> iteratingGardenList = new ArrayList<>();
            iteratingGardenList.addAll(results);

            for (Garden garden : iteratingGardenList) {
                if (!isTextInGardenFields(garden, searchValue)) {
                    results.remove(garden);
                }
            }
        }
        return results;
    }

    public static List<Garden> gardenSearchAllFeatures(String searchValueRaw, Iterable<Feature> allFeatures, List<Garden> allGardens) {
        String searchValue = searchValueRaw.toLowerCase();
        List<Garden> results = new ArrayList<>();

        if (searchValue.equals("all") || searchValue.equals("")){
            results = allGardens;
        }
        for (Garden garden : allGardens) {

            for(Feature feature : garden.getFeatures()) {

                if (feature.getName().toLowerCase().contains(searchValue)) {

                    if (!results.contains(garden)) {

                        results.add(garden);
                    }
                }
            }

            if (isTextInGardenFields(garden, searchValue)) {
                if (!results.contains(garden)) {
                    results.add(garden);
                }
            }
        }
        return results;
    }

    public static Feature getFeatureFromId(Integer featureId, Iterable<Feature> allFeatures) {

        for (Feature feature : allFeatures) {
            if (featureId.equals(feature.getId())) {
                return feature;
            }
        }
        return null;
    }

    public static boolean isTextInGardenFields(Garden curGarden, String searchValue) {

            if (curGarden.getName().toLowerCase().contains(searchValue) ||
                    curGarden.getDescription().toLowerCase().contains(searchValue) ||
                    curGarden.getAddress().toLowerCase().contains(searchValue) ||
                    curGarden.getOwner().getName().toLowerCase().contains(searchValue)) {

                return true;
            }

        return false;
    }

}