import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { BrowserRouter, Route } from 'react-router-dom';

import TuckletsNavBar from './common/TuckletsNavBar';
import HomePage from './pages/HomePage';
import AboutPage from './pages/AboutPage';
import OurStoryPage from './pages/OurStoryPage';
import NewslettersPage from './pages/NewslettersPage';
import SponsorChildPage from './pages/SponsorChildPage';
import SponsorInfoPage from './pages/SponsorInfoPage';
import Footer from './common/Footer';
import i18n from './common/i18n';

import 'bootstrap/dist/css/bootstrap.min.css'; 

import '../static/css/bootstrap-agency-theme.css';


class Main extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            selectedLocale: 'en-US', 
            selectedChildren: [], 
            // Initialize donation amount for inital render.
            sponsor: {donationAmount: 0},
            // Whether or not the Sponsor Info page should be displayed/whether redirection needs to occur from sponsor info page.
            shouldRedirectFromSponsorInfo: true,
            // Whether redirection is necessary from the sponsor child page.
            shouldRedirectFromSponsorChild: false
        };

        // Bind handlers
        this.handleSelectedLocaleChange = this.handleSelectedLocaleChange.bind(this);
        this.handleSponsorChildSubmission = this.handleSponsorChildSubmission.bind(this);
    }

    /**
     * Handler that updates the currently selected locale.
     */
    handleSelectedLocaleChange(event) {
        const selectedLocale = event.target.value;
        this.setState({ selectedLocale: selectedLocale });
        i18n.changeLanguage(selectedLocale);
    }

        /**
     * Handles form submission after user has selected the children he or she wants to sponsor.
     */
    handleSponsorChildSubmission(childrenSelections) {
        let self = this;
        return () => {
            let selectedChildrenIds = []
            for (let childId in childrenSelections) {
                if (childrenSelections[childId]) {
                    selectedChildrenIds.push(childId)
                }
            }
            let requestParams = selectedChildrenIds.join(',');
            axios.get('/sponsor-info/selections/', {
                params: {
                    childIds: requestParams
                }
            })
            .then(function (response) {
                console.log(response);
                self.setState({ 
                    selectedChildren: response.data.children, 
                    sponsor: response.data.sponsor,
                    shouldRedirectFromSponsorInfo: false,
                    shouldRedirectFromSponsorChild: true
                });
            })
            .catch(function (error) {
                console.log(error);
            });
        }

    }


    render() {
        if (this.state.focusedView) {
            // return this.state.focusedView;
            return <BrowserRouter>{this.state.focusedView}</BrowserRouter>
        }
        return (
            <div>
                <BrowserRouter>
                    <TuckletsNavBar handleSelectedLocaleChange={this.handleSelectedLocaleChange} i18n={i18n} />
                    <Route path="/sponsor-info/">
                        <SponsorInfoPage 
                            shouldRedirect={this.state.shouldRedirectFromSponsorInfo}
                            selectedChildren={this.state.selectedChildren} 
                            donationAmount={this.state.sponsor.donationAmount} 
                            i18n={i18n} handleSelectedLocaleChange={this.handleSelectedLocaleChange} 
                        />
                    </Route>
                    <Route exact path="/">
                        <HomePage i18n={i18n} handleSelectedLocaleChange={this.handleSelectedLocaleChange} />
                        <br />
                        <AboutPage i18n={i18n} handleSelectedLocaleChange={this.handleSelectedLocaleChange} numStudents={998} numTeachers={67}/>
                        <br />
                        <OurStoryPage i18n={i18n} handleSelectedLocaleChange={this.handleSelectedLocaleChange} />
                        <br />
                        <NewslettersPage i18n={i18n} handleSelectedLocaleChange={this.handleSelectedLocaleChange} />
                        <br />
                        <SponsorChildPage i18n={i18n} handleSelectedLocaleChange={this.handleSelectedLocaleChange} handleSponsorChildSubmission={this.handleSponsorChildSubmission} shouldRedirect={this.state.shouldRedirectFromSponsorChild} />
                        <br />
                    </Route>
                    
                    <Footer i18n={i18n} />
                </BrowserRouter>
            </div>
        )
    }

    // componentDidMount() {
    //     let name = '';
    //     axios.get('/test').then((response) => {
    //         console.log(response.data);
    //         this.setState({ name: response.data.name });
    //     })

    // }
}

ReactDOM.render(
    <Main />,
    document.getElementById('tucklets-home')
);