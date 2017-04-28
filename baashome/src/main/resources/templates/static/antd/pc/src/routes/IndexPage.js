import React, { Component, PropTypes } from 'react';
import { connect } from 'dva';
import { Link } from 'dva/router';
import Home from '../components/home/index';


function IndexPage() {
  return (

    <Home />

  );
}

IndexPage.propTypes = {
};

export default connect()(IndexPage);
