import React from 'react';
import ReactDOM from 'react-dom';
import { scrollScreen } from 'rc-scroll-anim';

import Nav from './Nav';
import Content0 from './Content0';
import Footer from './Footer';

import './less/antMotion_style.less';

export default class Home extends React.Component {
  componentDidMount() {
  }

  render() {
    const children = [
      <Nav id="Nav" key="Nav"/>,
      <Content0 id="Content0" key="Content0"/>,
      <Footer id="Footer" key="Footer"/>,
    ];
    return (
      <div className="templates-wrapper">
        {children}
      </div>
    );
  }
}
