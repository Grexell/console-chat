package by.dima.controller;

import by.dima.model.logic.service.RangeInfoService;
import by.dima.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InfoController {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "20";

    private RangeInfoService infoService;

    @Autowired
    public InfoController(RangeInfoService infoService) {
        this.infoService = infoService;
    }

    @RequestMapping(path = "/agents/", params = "free")
    public ResponseEntity getFreeAgents(@RequestParam(required = false, defaultValue = DEFAULT_PAGE_NUMBER) int pageNumber,
                                        @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(infoService.getFreeAgentIDs(PageCalculator.getStartIndex(pageNumber, pageSize), pageSize), HttpStatus.OK);
    }

    @RequestMapping("/agents/")
    public ResponseEntity getAllAgents(@RequestParam(required = false, defaultValue = DEFAULT_PAGE_NUMBER) int pageNumber,
                                       @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(infoService.getAllAgentIDs(PageCalculator.getStartIndex(pageNumber, pageSize), pageSize), HttpStatus.OK);
    }

    @RequestMapping("/agents/{agentId}")
    public ResponseEntity getAgentInfo(@PathVariable String agentId) {
        return new ResponseEntity<>(infoService.getAgentInfo(agentId), HttpStatus.OK);
    }

    @RequestMapping("/agents/count")
    public ResponseEntity getFreeAgentAmount() {
        return new ResponseEntity<>(infoService.getFreeAgentIDs().size(), HttpStatus.OK);
    }

    @RequestMapping("/chats")
    public ResponseEntity getChats(@RequestParam(required = false, defaultValue = DEFAULT_PAGE_NUMBER) int pageNumber,
                                   @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(infoService.getOpenedChatIDs(PageCalculator.getStartIndex(pageNumber, pageSize), pageSize), HttpStatus.OK);
    }

    @RequestMapping("/chats/{chatId}")
    public ResponseEntity getChatInfo(@PathVariable String chatId) {
        return new ResponseEntity<>(infoService.getChatDetails(chatId), HttpStatus.OK);
    }

    @RequestMapping(path = "/clients/", params = "queued")
    public ResponseEntity getQueuedClients(@RequestParam(required = false, defaultValue = DEFAULT_PAGE_NUMBER) int pageNumber,
                                           @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(infoService.getQueuedUserIDs(PageCalculator.getStartIndex(pageNumber, pageSize), pageSize), HttpStatus.OK);
    }

    @RequestMapping("/clients/{userId}")
    public ResponseEntity getUser(@PathVariable String userId) {
        return new ResponseEntity<>(infoService.getUserInfo(userId), HttpStatus.OK);
    }
}
