import  { useState } from "react";
import { AppBar, Toolbar, Typography, Button, IconButton, Menu, MenuItem, Box, Container } from "@mui/material";
// import MenuIcon from "@mui/material/Menu";
import { Menu as MenuIcon} from "lucide-react";
import {Link, useNavigate} from "react-router-dom";

const Navbar = () => {
    const [anchorEl, setAnchorEl] = useState(null);
    const openMenu = Boolean(anchorEl);
    const navigate = useNavigate();

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


                    <Box sx={{ flexGrow: 1, display: "flex", alignItems: "center",justifyContent : "space-between",ml : -23 }}>
                        {/* Menu Icon on the Left */}
                        <IconButton
                            size="large"
                            color="inherit"
                            aria-label="menu"
                            onClick={handleMenuOpen}
                            sx={{
                                marginRight: "auto" // This ensures the icon stays on the left
                            }}
                        >
                            <MenuIcon />
                        </IconButton>

                        {/* Logo and Name on the Right */}
                        <Link to="/" style={{ textDecoration: "none", display: "flex", alignItems: "center" }}>
                            <img
                                src="https://flowbite.com/docs/images/logo.svg"
                                alt="Logo"
                                style={{ height: "40px", marginRight: "10px",marginLeft : "-300px" }}
                            />
                            <Typography variant="h6" sx={{ fontWeight: "bold", color: "white" }}>
                                Expense Tracker
                            </Typography>
                        </Link>

                    <Menu
                            anchorEl={anchorEl}
                            open={openMenu}
                            onClose={handleMenuClose}
                            sx={{ mt: "45px" }}
                        >

                            <MenuItem onClick={() => navigate("/Dashboard")}>Dashboard</MenuItem>
                            <MenuItem onClick={() => navigate("Expense")}>Expense</MenuItem>
                            <MenuItem onClick={() => navigate("/Revenue")}>Revenue</MenuItem>
                            <MenuItem onClick={() => navigate("/Report")}>Report</MenuItem>
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