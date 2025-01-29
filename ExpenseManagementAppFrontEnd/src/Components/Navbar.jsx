import React, { useState } from "react";
import { AppBar, Toolbar, Typography, Button, IconButton, Menu, MenuItem, Box, Container } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import { Link } from "react-router-dom";

const Navbar = () => {
  const [anchorEl, setAnchorEl] = useState(null);
  const openMenu = Boolean(anchorEl);

  const handleMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  return (
    <AppBar position="fixed" color="primary" sx={{ zIndex: 1201 }}>
      <Container maxWidth="lg">
        <Toolbar disableGutters>
          {/* Logo and title */}
          <Link to="/" style={{ textDecoration: "none", display: "flex", alignItems: "center" }}>
            <img
              src="https://flowbite.com/docs/images/logo.svg"
              alt="Logo"
              style={{ height: "40px", marginRight: "10px" }}
            />
            <Typography variant="h6" sx={{ fontWeight: "bold", color: "white" }}>
              Expense Tracker
            </Typography>
          </Link>

          {/* Menu Button for Mobile View */}
          <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
            <IconButton
              size="large"
              edge="start"
              color="inherit"
              aria-label="menu"
              onClick={handleMenuOpen}
              sx={{ marginRight: 2 }}
            >
              <MenuIcon />
            </IconButton>

            <Menu
              anchorEl={anchorEl}
              open={openMenu}
              onClose={handleMenuClose}
              sx={{ mt: "45px" }}
            >
              <MenuItem onClick={handleMenuClose}>Home</MenuItem>
              <MenuItem onClick={handleMenuClose}>About</MenuItem>
              <MenuItem onClick={handleMenuClose}>Services</MenuItem>
              <MenuItem onClick={handleMenuClose}>Contact</MenuItem>
            </Menu>
          </Box>

          {/* Desktop Menu */}
          <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" }, justifyContent: "flex-end" }}>
            <Button color="inherit" sx={{ marginRight: 2 }}>
              Home
            </Button>
            <Button color="inherit" sx={{ marginRight: 2 }}>
              About
            </Button>
            <Button color="inherit" sx={{ marginRight: 2 }}>
              Services
            </Button>
            <Button color="inherit" sx={{ marginRight: 2 }}>
              Contact
            </Button>
          </Box>

          {/* Authentication Buttons */}
          <Box sx={{ display: "flex" }}>
            <Button color="inherit" sx={{ marginRight: 1 }}>
              Register
            </Button>
            <Button color="inherit" sx={{ marginRight: 1 }}>
              Login
            </Button>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
};

export default Navbar;
