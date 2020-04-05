package com.tucklets.app.controllers;

import com.tucklets.app.containers.LocaleContainer;
import com.tucklets.app.containers.SponsorInfoContainer;
import com.tucklets.app.entities.Child;
import com.tucklets.app.entities.Sponsor;
import com.tucklets.app.entities.enums.DonationDuration;
import com.tucklets.app.services.AmountService;
import com.tucklets.app.services.ChildAndSponsorAssociationService;
import com.tucklets.app.services.ChildService;
import com.tucklets.app.services.SponsorService;
import com.tucklets.app.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/sponsor-info")
public class SponsorInfoController {

    @Autowired
    SponsorService sponsorService;

    @Autowired
    ChildAndSponsorAssociationService childAndSponsorAssociationService;

    @Autowired
    ChildService childService;

    @Autowired
    AmountService amountService;

    @GetMapping(value = "/")
    public ModelAndView handleChildSelection(@RequestParam(value = "childId") String[] childrenIds) {
        ModelAndView modelAndView = new ModelAndView("sponsor-info");

        var selectedChildren = childService.fetchChildByIds(childrenIds);
        Locale locale = LocaleContextHolder.getLocale();
        var totalDonationAmount = amountService.computeTotalDonationAmount(selectedChildren);
        Sponsor sponsor = new Sponsor();
        sponsor.setDonationAmount(totalDonationAmount);

        LocaleContainer localeContainer = new LocaleContainer(Constants.SUPPORTED_LOCALES, locale);
        SponsorInfoContainer sponsorInfoContainer = new SponsorInfoContainer(
            sponsor,
            selectedChildren,
            DonationDuration.getAllDonationDurations(),
            childrenIds,
            null,
            selectedChildren.size());
        sponsorInfoContainer.setNumChildren(selectedChildren.size());

        modelAndView.addObject("localeContainer", localeContainer);
        modelAndView.addObject("sponsorInfoContainer", sponsorInfoContainer);
        return modelAndView;
    }

    @PostMapping(value = "/submit")
    public String handleSponsorSubmission(@ModelAttribute SponsorInfoContainer sponsorInfoContainer) {

        // TODO: Validate form.

        List<Child> selectedChildren = sponsorInfoContainer.getChildren();
        Sponsor sponsor = sponsorInfoContainer.getSponsor();
        DonationDuration donationDuration = sponsorInfoContainer.getSelectedDonationDuration();

        sponsorService.addSponsor(sponsor);
        childAndSponsorAssociationService.createAssociation(selectedChildren, sponsor, donationDuration);
        childService.setSponsoredChildren(selectedChildren);

        return "success";
    }
}
