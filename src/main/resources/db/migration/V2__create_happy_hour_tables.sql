-- Establishments (bars and restaurants in Belmont Shore)
CREATE TABLE establishments (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,  -- 'bar', 'restaurant', 'bar_restaurant'
    address VARCHAR(500),
    phone VARCHAR(50),
    website VARCHAR(255),
    instagram VARCHAR(255),
    facebook VARCHAR(255),
    description TEXT,
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    last_verified_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Happy hour schedules
CREATE TABLE happy_hours (
    id VARCHAR(255) PRIMARY KEY,
    establishment_id VARCHAR(255) NOT NULL,
    day_of_week INTEGER NOT NULL,  -- 0=Sunday, 1=Monday, ..., 6=Saturday
    start_time VARCHAR(10) NOT NULL,  -- HH:MM format
    end_time VARCHAR(10) NOT NULL,    -- HH:MM format
    last_verified_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (establishment_id) REFERENCES establishments(id)
);

-- Happy hour specials with detailed pricing
CREATE TABLE specials (
    id VARCHAR(255) PRIMARY KEY,
    happy_hour_id VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    item_name VARCHAR(255),
    item_type VARCHAR(50),  -- 'drink', 'food', 'both'
    happy_hour_price DECIMAL(10, 2),
    original_price DECIMAL(10, 2),
    discount_info VARCHAR(255),
    last_verified_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (happy_hour_id) REFERENCES happy_hours(id)
);

-- Chat message history (optional, for analytics)
CREATE TABLE chat_messages (
    id VARCHAR(255) PRIMARY KEY,
    user_session_id VARCHAR(255),
    message_type VARCHAR(20) NOT NULL,  -- 'user' or 'bot'
    content TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Data scrape logs for tracking freshness
CREATE TABLE scrape_logs (
    id VARCHAR(255) PRIMARY KEY,
    establishment_id VARCHAR(255),
    source VARCHAR(50) NOT NULL,  -- 'website', 'instagram', 'facebook', 'manual'
    status VARCHAR(20) NOT NULL,  -- 'success', 'failed', 'partial'
    notes TEXT,
    scraped_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (establishment_id) REFERENCES establishments(id)
);
